package com.travel_ease.travel_ease.service;

import com.travel_ease.travel_ease.entity.*;
import com.travel_ease.travel_ease.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Services {
    @Service public static class TravelInterest {
        @Autowired TravelInterestRepository repo;
        public com.travel_ease.travel_ease.entity.TravelInterest save(com.travel_ease.travel_ease.entity.TravelInterest t){ return repo.save(t); }
        public List<com.travel_ease.travel_ease.entity.TravelInterest> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.TravelInterest> findById(UUID id){ return repo.findById(id); }
        public com.travel_ease.travel_ease.entity.TravelInterest update(UUID id, com.travel_ease.travel_ease.entity.TravelInterest t){ t.setId(id); return repo.save(t); }
        public void delete(UUID id){ repo.deleteById(id); }
        public List<com.travel_ease.travel_ease.entity.TravelInterest> findUnused(){ return repo.findUnused(); }
    }
    @Service public static class AgencyBranch {
        @Autowired AgencyBranchRepository repo;
        public com.travel_ease.travel_ease.entity.AgencyBranch save(com.travel_ease.travel_ease.entity.AgencyBranch a){ return repo.save(a); }
        public List<com.travel_ease.travel_ease.entity.AgencyBranch> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.AgencyBranch> findById(UUID id){ return repo.findById(id); }
        public com.travel_ease.travel_ease.entity.AgencyBranch update(UUID id, com.travel_ease.travel_ease.entity.AgencyBranch a){ a.setId(id); return repo.save(a); }
        public void delete(UUID id){ repo.deleteById(id); }
        public List<com.travel_ease.travel_ease.entity.AgencyBranch> findFree(){ return repo.findFree(); }
        public Optional<com.travel_ease.travel_ease.entity.AgencyBranch> findByBranchNumber(int n){ return repo.findByBranchNumber(n); }
    }
    @Service public static class TravelAgent {
        @Autowired TravelAgentRepository repo;
        @Autowired TravelInterestRepository tiRepo;
        @Autowired AgencyBranchRepository abRepo;
        public com.travel_ease.travel_ease.entity.TravelAgent save(com.travel_ease.travel_ease.entity.TravelAgent t){ return repo.save(t); }
        public List<com.travel_ease.travel_ease.entity.TravelAgent> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.TravelAgent> findById(UUID id){ return repo.findById(id); }
        public com.travel_ease.travel_ease.entity.TravelAgent update(UUID id, com.travel_ease.travel_ease.entity.TravelAgent t){ t.setId(id); return repo.save(t); }
        public void delete(UUID id){ repo.deleteById(id); }
        public List<com.travel_ease.travel_ease.entity.TravelAgent> findByTravelInterest(String name){ return repo.findByTravelInterestName(name); }
        public String addInterest(UUID agentId, UUID interestId){
            Optional<com.travel_ease.travel_ease.entity.TravelAgent> a = repo.findById(agentId);
            if(a.isEmpty()) return "NA";
            Optional<com.travel_ease.travel_ease.entity.TravelInterest> ti = tiRepo.findById(interestId);
            if(ti.isEmpty()) return "NI";
            if(a.get().getTravelInterests().contains(ti.get())) return "DUP";
            a.get().getTravelInterests().add(ti.get());
            repo.save(a.get());
            return "OK";
        }
        public String changeBranch(UUID agentId, int branchNumber){
            Optional<com.travel_ease.travel_ease.entity.TravelAgent> a = repo.findById(agentId);
            if(a.isEmpty()) return "NA";
            Optional<com.travel_ease.travel_ease.entity.AgencyBranch> b = abRepo.findByBranchNumber(branchNumber);
            if(b.isEmpty()) return "NB";
            if(repo.existsByAgencyBranch(b.get()) && (a.get().getAgencyBranch()==null || !a.get().getAgencyBranch().getId().equals(b.get().getId()))) return "TAKEN";
            a.get().setAgencyBranch(b.get());
            repo.save(a.get());
            return "OK";
        }
    }
    @Service public static class Traveler {
        @Autowired TravelerRepository repo;
        @Autowired TravelAgentRepository taRepo;
        public com.travel_ease.travel_ease.entity.Traveler save(com.travel_ease.travel_ease.entity.Traveler t){ return repo.save(t); }
        public List<com.travel_ease.travel_ease.entity.Traveler> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.Traveler> findById(UUID id){ return repo.findById(id); }
        public com.travel_ease.travel_ease.entity.Traveler update(UUID id, com.travel_ease.travel_ease.entity.Traveler t){ t.setId(id); return repo.save(t); }
        public void delete(UUID id){ repo.deleteById(id); }
        public List<com.travel_ease.travel_ease.entity.Traveler> byNationality(String n){ return repo.findByNationalityIgnoreCaseOrderByLastNameAsc(n); }
        public List<com.travel_ease.travel_ease.entity.Traveler> ofAgent(UUID id){ return repo.findByTravelAgentId(id); }
        public boolean agentExists(UUID id){ return !taRepo.existsById(id); }
    }
    @Service public static class TripPlan {
        @Autowired TripPlanRepository repo;
        @Autowired TravelAgentRepository taRepo;
        @Autowired TravelerRepository trRepo;
        @Autowired TripExpenseRepository teRepo;
        public com.travel_ease.travel_ease.entity.TripPlan save(com.travel_ease.travel_ease.entity.TripPlan t, String[] err){
            if(t.getDepartureDate().isBefore(LocalDate.now())) { err[0]="PAST"; return null; }
            if(t.getTravelAgent()!=null && repo.existsByTravelAgentAndDepartureDateAndStatusNot(t.getTravelAgent(), t.getDepartureDate(), com.travel_ease.travel_ease.entity.TripStatus.ABANDONED)) { err[0]="DUP"; return null; }
            t.setStatus(com.travel_ease.travel_ease.entity.TripStatus.DRAFTED);
            return repo.save(t);
        }
        public List<com.travel_ease.travel_ease.entity.TripPlan> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.TripPlan> findById(UUID id){ return repo.findById(id); }
        public com.travel_ease.travel_ease.entity.TripPlan update(UUID id, com.travel_ease.travel_ease.entity.TripPlan t){ t.setId(id); t.setStatus(repo.findById(id).get().getStatus()); return repo.save(t); }
        public String delete(UUID id){
            Optional<com.travel_ease.travel_ease.entity.TripPlan> tp = repo.findById(id);
            if(tp.isEmpty()) return "NF";
            if(teRepo.existsByTripPlan(tp.get())) return "HAS";
            repo.deleteById(id);
            return "OK";
        }
        public List<com.travel_ease.travel_ease.entity.TripPlan> byStatus(com.travel_ease.travel_ease.entity.TripStatus s){ return repo.findByStatusOrderByDepartureDateAsc(s); }
        public List<com.travel_ease.travel_ease.entity.TripPlan> between(LocalDate s, LocalDate e){ return repo.findByDepartureDateBetweenOrderByDepartureDateAsc(s,e); }
        public List<Object[]> stats(){ return repo.countByStatus(); }
        public int cancelDay(UUID agentId, LocalDate date){ return repo.cancelDay(agentId, date); }
        public org.springframework.data.domain.Page<com.travel_ease.travel_ease.entity.TripPlan> findPage(org.springframework.data.domain.Pageable p){ return repo.findAll(p); }
        public com.travel_ease.travel_ease.entity.TripPlan changeStatus(UUID id, com.travel_ease.travel_ease.entity.TripStatus newStatus){
            Optional<com.travel_ease.travel_ease.entity.TripPlan> tp = repo.findById(id);
            if(tp.isEmpty()) return null;
            com.travel_ease.travel_ease.entity.TripStatus cur = tp.get().getStatus();
            boolean ok = false;
            if(cur==com.travel_ease.travel_ease.entity.TripStatus.DRAFTED && (newStatus==com.travel_ease.travel_ease.entity.TripStatus.FINALIZED || newStatus==com.travel_ease.travel_ease.entity.TripStatus.ABANDONED)) ok=true;
            if(cur==com.travel_ease.travel_ease.entity.TripStatus.FINALIZED && (newStatus==com.travel_ease.travel_ease.entity.TripStatus.TRAVELLED || newStatus==com.travel_ease.travel_ease.entity.TripStatus.ABANDONED)) ok=true;
            if(!ok) return null; // sentinel for invalid; controller distinguishes 404 vs 400 by checking existence separately
            tp.get().setStatus(newStatus);
            return repo.save(tp.get());
        }
    }
    @Service public static class TripExpense {
        @Autowired TripExpenseRepository repo;
        @Autowired TripPlanRepository tpRepo;
        public com.travel_ease.travel_ease.entity.TripExpense save(com.travel_ease.travel_ease.entity.TripExpense te, UUID tripPlanId, String[] err){
            Optional<com.travel_ease.travel_ease.entity.TripPlan> tp = tpRepo.findById(tripPlanId);
            if(tp.isEmpty()){ err[0]="NF"; return null; }
            if(tp.get().getStatus()!=com.travel_ease.travel_ease.entity.TripStatus.TRAVELLED){ err[0]="NOTTRAVELLED"; return null; }
            te.setTripPlan(tp.get());
            te.setRecordedDate(LocalDate.now());
            return repo.save(te);
        }
        public List<com.travel_ease.travel_ease.entity.TripExpense> findAll(){ return repo.findAll(); }
        public Optional<com.travel_ease.travel_ease.entity.TripExpense> findById(UUID id){ return repo.findById(id); }
        public void delete(UUID id){ repo.deleteById(id); }
        public List<com.travel_ease.travel_ease.entity.TripExpense> byTraveler(String passport){ return repo.findByTravelerPassport(passport); }
    }
    @Service public static class Dashboard {
        @Autowired TravelerRepository trRepo;
        @Autowired TravelAgentRepository taRepo;
        @Autowired AgencyBranchRepository abRepo;
        @Autowired TripPlanRepository tpRepo;
        @Autowired TripExpenseRepository teRepo;
        public java.util.Map<String,Long> summary(){
            java.util.Map<String,Long> m = new java.util.LinkedHashMap<>();
            m.put("travelers", trRepo.count());
            m.put("travelAgents", taRepo.count());
            m.put("freeAgencyBranches", (long) abRepo.findFree().size());
            m.put("departuresToday", tpRepo.countByDepartureDate(LocalDate.now()));
            m.put("drafted", tpRepo.countByStatus(com.travel_ease.travel_ease.entity.TripStatus.DRAFTED));
            m.put("expenses", teRepo.count());
            return m;
        }
    }
}