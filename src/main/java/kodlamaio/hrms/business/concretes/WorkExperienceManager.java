package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkExperienceDao;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.WorkExperience;
import kodlamaio.hrms.entities.dtos.WorkExperienceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceManager implements WorkExperienceService {

    private final WorkExperienceDao workExperienceDao;

    public WorkExperienceManager(WorkExperienceDao workExperienceDao) {
        this.workExperienceDao = workExperienceDao;
    }

    @Override
    public DataResult<List<WorkExperience>> getAll() {
        return new SuccessDataResult<>(workExperienceDao.findAll());
    }

    @Override
    public Result add(WorkExperienceDto workExperienceDto) {
        WorkExperience workExperience = workExperienceDtoToWorkExperienceConverter(workExperienceDto);
        workExperienceDao.save(workExperience);
        return new SuccessResult();
    }

    @Override
    public Result delete(WorkExperience workExperience) {
        workExperienceDao.delete(workExperience);
        return new SuccessResult();
    }

    @Override
    public Result update(WorkExperience workExperience) {
        workExperienceDao.save(workExperience);
        return new SuccessResult();
    }

    //This method converts the WorkExperienceDto object into a form that the database will recognize.
    private WorkExperience workExperienceDtoToWorkExperienceConverter(WorkExperienceDto workExperienceDto){
        //This value will hold the curriculumVitaeId of the logged-in user when the JSON Web Token was written.
        int currentCurriculumVitaeId = 1;

        CurriculumVitae curriculumVitae = new CurriculumVitae(currentCurriculumVitaeId);

        return new WorkExperience(curriculumVitae, workExperienceDto.getCompanyName(), workExperienceDto.getPositionName(), workExperienceDto.getStartYear(), workExperienceDto.getQuitYear());
    }
}
