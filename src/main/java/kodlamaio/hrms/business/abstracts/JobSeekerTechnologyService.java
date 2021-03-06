package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeekerTechnology;

import java.util.List;

public interface JobSeekerTechnologyService {
    DataResult<List<JobSeekerTechnology>> getAll();
    DataResult<JobSeekerTechnology> getById(int id);
    Result add(JobSeekerTechnology jobSeekerTechnology);
}
