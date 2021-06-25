package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CurriculaVitaeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CurriculaVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerTechnologyDao;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaDao;
import kodlamaio.hrms.entities.concretes.CurriculaVitae;
import kodlamaio.hrms.entities.concretes.JobSeekerTechnology;
import kodlamaio.hrms.entities.concretes.SocialMedia;
import kodlamaio.hrms.entities.dtos.CurriculaVitaeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculaVitaeManager implements CurriculaVitaeService {

    private final CurriculaVitaeDao curriculaVitaeDao;
    private final SocialMediaDao socialMediaDao;
    private final JobSeekerTechnologyDao jobSeekerTechnologyDao;

    @Autowired
    public CurriculaVitaeManager(CurriculaVitaeDao curriculaVitaeDao, SocialMediaDao socialMediaDao, JobSeekerTechnologyDao jobSeekerTechnologyDao) {
        this.curriculaVitaeDao = curriculaVitaeDao;
        this.socialMediaDao = socialMediaDao;
        this.jobSeekerTechnologyDao = jobSeekerTechnologyDao;
    }

    @Override
    public DataResult<List<CurriculaVitae>> getAll() {
        return new SuccessDataResult<>
                (this.curriculaVitaeDao.findAll(),"Data listed");
    }

    @Override
    public DataResult<CurriculaVitae> getById(int id) {
        return new SuccessDataResult<>
                (this.curriculaVitaeDao.getById(id));
    }

    @Override
    public Result add(CurriculaVitae curriculaVitae) {
        this.curriculaVitaeDao.save(curriculaVitae);
        return new SuccessResult("Added");
    }

    @Override
    public Result update(int id , String coverLetter,String githubUsername, String linkedinUsername, String technologyName) {
        CurriculaVitae curriculaVitae = curriculaVitaeDao.getById(id);
        SocialMedia socialMedia=socialMediaDao.getByCurriculaVitaes_Id(id);
        JobSeekerTechnology jobSeekerTechnology=jobSeekerTechnologyDao.getById(id);
        socialMedia.setGithubUsername(githubUsername);
        socialMedia.setLinkedinUsername(linkedinUsername);
        jobSeekerTechnology.setTechnologyName(technologyName);
        curriculaVitae.setCoverLetter(coverLetter);
        curriculaVitaeDao.save(curriculaVitae);
        return new SuccessResult("Updated");
    }

    @Override
    public DataResult<List<CurriculaVitaeDto>> getAllDto() {
        return new SuccessDataResult<>
                (this.curriculaVitaeDao.getAllDto());
    }
}
