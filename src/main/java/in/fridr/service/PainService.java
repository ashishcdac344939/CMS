package in.fridr.service;

import java.util.List;

import in.fridr.entity.PainMaster;

public interface PainService {

	
	PainMaster addPainMaster(PainMaster painMaster);

	List<PainMaster> getAllPainDetails();
}
