package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {

    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) { this.jobAdvertisementService = jobAdvertisementService; }

    @GetMapping("/getAll")
    public DataResult<List<JobAdvertisement>> getAll(){
        return jobAdvertisementService.getAll();
    }

    @GetMapping("/getByActivityStatus")
    public DataResult<List<JobAdvertisement>> getByActivityStatus(){
        return jobAdvertisementService.getByActivityStatus();
    }

    @GetMapping("/getByActivityStatusSorted")
    public DataResult<List<JobAdvertisement>> getByActivityStatusSorted(){
        return jobAdvertisementService.getByActivityStatusSorted();
    }

    @GetMapping("/getByEmployerIdAndActivityStatus")
    public DataResult<List<JobAdvertisement>> getByEmployer_IdAndActivityStatus(@RequestParam Integer employerId){
        return jobAdvertisementService.getByEmployer_IdAndActivityStatus(employerId);
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvertisementDto jobAdvertisementDto){
        return jobAdvertisementService.add(jobAdvertisementDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody JobAdvertisement jobAdvertisement){
        return jobAdvertisementService.delete(jobAdvertisement);
    }

    @PutMapping("/update")
    public Result update(@RequestBody JobAdvertisement jobAdvertisement){
        return jobAdvertisementService.update(jobAdvertisement);
    }
}
