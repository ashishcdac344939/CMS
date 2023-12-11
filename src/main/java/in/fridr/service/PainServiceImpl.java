package in.fridr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fridr.entity.PainMaster;
import in.fridr.repository.PainMasterRepo;
@Service
@Transactional
public class PainServiceImpl implements PainService {

	@Autowired
	private PainMasterRepo painMasterRepo;
	
	@Override
	public PainMaster addPainMaster(PainMaster painMaster) {
		return painMasterRepo.save(painMaster);
	}

	@Override
	public List<PainMaster> getAllPainDetails() {
		return painMasterRepo.findAll();
	}

	
}
