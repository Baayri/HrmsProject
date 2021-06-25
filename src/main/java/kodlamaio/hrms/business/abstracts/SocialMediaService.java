package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMedia;

import java.util.List;

public interface SocialMediaService {
    DataResult<List<SocialMedia>> getAll();
    DataResult<SocialMedia> getByCurriculaVitaeId(int id);
    Result add(SocialMedia socialMedia);
}
