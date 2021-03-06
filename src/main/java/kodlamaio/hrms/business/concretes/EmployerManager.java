package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ConfirmationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final ConfirmationService confirmationService;

    @Autowired
    public EmployerManager(EmployerDao employerDao,ConfirmationService confirmationService){
        this.employerDao=employerDao;
        this.confirmationService=confirmationService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>
                (this.employerDao.findAll(),"Data listed");
    }

    @Override
    public DataResult<Employer> getById(int id) {
        return new SuccessDataResult<>
                (this.employerDao.getById(id));
    }

    @Override
    public Result add(Employer employer) {
        if(getByEmail(employer.getEmail()).getData()!=null){
            return new ErrorResult("Email already exist");
        }
        else if (!confirmationService.isConfirmed(employer)){
            return new ErrorResult("Email not verified");
        }
        this.employerDao.save(employer);
        return new SuccessResult("Added");
    }

    @Override
    public Result update(int id, String companyName, String website, String phone) {
        Employer employer=employerDao.getById(id);
        employer.setCompanyName(companyName);
        employer.setWebsite(website);
        employer.setPhone(phone);
        employerDao.save(employer);
        return new SuccessResult("Updated");
    }

    @Override
    public DataResult<Employer> getByEmail(String email) {
        return new SuccessDataResult<>
                (employerDao.findByEmail(email));
    }
}
