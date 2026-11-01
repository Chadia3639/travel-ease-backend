package com.travel_ease.travel_ease.controller;

import com.travel_ease.travel_ease.entity.*;
import com.travel_ease.travel_ease.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/travel-interests")
class TravelInterestController {
    @Autowired Services.TravelInterest svc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestBody TravelInterest t){ svc.save(t); return ResponseEntity.status(201).body("Travel interest saved successfully"); }
    @GetMapping("/all") public List<TravelInterest> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The travel interest with that id does not exist"));
    }
    @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TravelInterest t){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The travel interest with that id does not exist");
        return ResponseEntity.ok(svc.update(id,t));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The travel interest with that id does not exist");
        svc.delete(id); return ResponseEntity.ok("Travel interest deleted successfully");
    }
    @GetMapping("/unused") public List<TravelInterest> unused(){ return svc.findUnused(); }
}

@RestController
@RequestMapping("/api/agency-branches")
class AgencyBranchController {
    @Autowired Services.AgencyBranch svc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestBody AgencyBranch a){ svc.save(a); return ResponseEntity.status(201).body("Agency branch saved successfully"); }
    @GetMapping("/all") public List<AgencyBranch> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The agency branch with that id does not exist"));
    }
    @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AgencyBranch a){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The agency branch with that id does not exist");
        return ResponseEntity.ok(svc.update(id,a));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The agency branch with that id does not exist");
        svc.delete(id); return ResponseEntity.ok("Agency branch deleted successfully");
    }
    @GetMapping("/free") public List<AgencyBranch> free(){ return svc.findFree(); }
}

@RestController
@RequestMapping("/api/travel-agents")
class TravelAgentController {
    @Autowired Services.TravelAgent svc;
    @Autowired Services.TravelInterest tiSvc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestBody TravelAgent t){ svc.save(t); return ResponseEntity.status(201).body("Travel agent saved successfully"); }
    @GetMapping("/all") public List<TravelAgent> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The travel agent with that id does not exist"));
    }
    @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TravelAgent t){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The travel agent with that id does not exist");
        return ResponseEntity.ok(svc.update(id,t));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The travel agent with that id does not exist");
        svc.delete(id); return ResponseEntity.ok("Travel agent deleted successfully");
    }
    @GetMapping("/by-travel-interest") public List<TravelAgent> byInterest(@RequestParam String name){ return svc.findByTravelInterest(name); }
    @PutMapping("/{agentId}/travel-interests/{interestId}") public ResponseEntity<?> addInterest(@PathVariable UUID agentId, @PathVariable UUID interestId){
        String r = svc.addInterest(agentId, interestId);
        if(r.equals("NA")) return ResponseEntity.status(404).body("The travel agent with that id does not exist");
        if(r.equals("NI")) return ResponseEntity.status(404).body("That travel interest does not exist");
        if(r.equals("DUP")) return ResponseEntity.status(409).body("The travel agent already has that travel interest");
        return ResponseEntity.ok("Travel interest added to the travel agent");
    }
    @PatchMapping("/{agentId}/agency-branch") public ResponseEntity<?> changeBranch(@PathVariable UUID agentId, @RequestParam int branchNumber){
        String r = svc.changeBranch(agentId, branchNumber);
        if(r.equals("NA")) return ResponseEntity.status(404).body("The travel agent with that id does not exist");
        if(r.equals("NB")) return ResponseEntity.status(404).body("The agency branch with that branchNumber does not exist");
        if(r.equals("TAKEN")) return ResponseEntity.status(409).body("That agency branch is taken by another travel agent");
        return ResponseEntity.ok("Agency branch changed successfully");
    }
}

@RestController
@RequestMapping("/api/travelers")
class TravelerController {
    @Autowired Services.Traveler svc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestBody Traveler t){ svc.save(t); return ResponseEntity.status(201).body("Traveler saved successfully"); }
    @GetMapping("/all") public List<Traveler> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The traveler with that id does not exist"));
    }
    @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Traveler t){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The traveler with that id does not exist");
        return ResponseEntity.ok(svc.update(id,t));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The traveler with that id does not exist");
        svc.delete(id); return ResponseEntity.ok("Traveler deleted successfully");
    }
    @GetMapping("/by-nationality") public List<Traveler> byNat(@RequestParam String nationality){ return svc.byNationality(nationality); }
    @GetMapping("/of-travel-agent/{travelAgentId}") public ResponseEntity<?> ofAgent(@PathVariable UUID travelAgentId){
        // need existence check
        return ResponseEntity.ok(svc.ofAgent(travelAgentId));
    }
}

@RestController
@RequestMapping("/api/trip-plans")
class TripPlanController {
    @Autowired Services.TripPlan svc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestBody TripPlan t){
        String[] err = new String[1];
        TripPlan res = svc.save(t, err);
        if(res==null){
            if("PAST".equals(err[0])) return ResponseEntity.status(400).body("Trip plan date cannot be in the past");
            return ResponseEntity.status(409).body("Travel agent already has a departure on that date");
        }
        return ResponseEntity.status(201).body("Trip plan saved successfully");
    }
    @GetMapping("/all") public List<TripPlan> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The trip plan with that id does not exist"));
    }
    @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TripPlan t){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The trip plan with that id does not exist");
        return ResponseEntity.ok(svc.update(id,t));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        String r = svc.delete(id);
        if(r.equals("NF")) return ResponseEntity.status(404).body("The trip plan with that id does not exist");
        if(r.equals("HAS")) return ResponseEntity.status(409).body("That trip plan has trip expenses, delete them first");
        return ResponseEntity.ok("Trip plan deleted successfully");
    }
    @GetMapping("/by-status") public List<TripPlan> byStatus(@RequestParam TripStatus status){ return svc.byStatus(status); }
    @GetMapping("/between") public List<TripPlan> between(@RequestParam String start, @RequestParam String end){
        return svc.between(LocalDate.parse(start), LocalDate.parse(end));
    }
    @PatchMapping("/{id}/status") public ResponseEntity<?> status(@PathVariable UUID id, @RequestParam TripStatus status){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The trip plan with that id does not exist");
        TripPlan tp = svc.changeStatus(id, status);
        if(tp==null) return ResponseEntity.status(400).body("Cannot change status from "+svc.findById(id).get().getStatus()+" to "+status);
        return ResponseEntity.ok("Trip plan status updated successfully");
    }
    @GetMapping("/stats/by-status") public List<Object[]> stats(){ return svc.stats(); }
    @PatchMapping("/cancel-day") public ResponseEntity<?> cancelDay(@RequestParam UUID travelAgentId, @RequestParam String date){
        int n = svc.cancelDay(travelAgentId, LocalDate.parse(date));
        return ResponseEntity.ok(n+" trip plans cancelled");
    }
}

@RestController
@RequestMapping("/api/trip-expenses")
class TripExpenseController {
    @Autowired Services.TripExpense svc;
    @PostMapping("/save") public ResponseEntity<?> save(@RequestParam UUID tripPlanId, @RequestBody TripExpense te){
        String[] err = new String[1];
        TripExpense res = svc.save(te, tripPlanId, err);
        if(res==null){
            if("NF".equals(err[0])) return ResponseEntity.status(404).body("The trip plan with that id does not exist");
            return ResponseEntity.status(400).body("Trip expenses are only for TRAVELLED trip plans");
        }
        return ResponseEntity.status(201).body("Trip expense saved successfully");
    }
    @GetMapping("/all") public List<TripExpense> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable UUID id){
        return svc.findById(id).map(x->ResponseEntity.ok((Object)x)).orElse(ResponseEntity.status(404).body("The trip expense with that id does not exist"));
    }
    @DeleteMapping("/delete/{id}") public ResponseEntity<?> delete(@PathVariable UUID id){
        if(svc.findById(id).isEmpty()) return ResponseEntity.status(404).body("The trip expense with that id does not exist");
        svc.delete(id); return ResponseEntity.ok("Trip expense deleted successfully");
    }
    @GetMapping("/traveler/{passportNumber}") public List<TripExpense> byTraveler(@PathVariable String passportNumber){ return svc.byTraveler(passportNumber); }
}

@RestController
@RequestMapping("/api/dashboard")
class DashboardController {
    @Autowired Services.Dashboard svc;
    @GetMapping("/summary") public Map<String,Long> summary(){ return svc.summary(); }
}




