package in.fridr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface MedicineQuantityMapProjection {
	    @Value("#{target.medicineName}")
	    String getMedicineName();
	    
	    @Value("#{target.quantity}")
	    Long getQuantity();
	    
}
