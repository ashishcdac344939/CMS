package in.fridr.modal;

public class MedicineAndQuantityModel {
	
		private int medicineId;
		private int medicineQuantity;
		private int noOfDays;
		private int daysInterval;
		private int medicineInaDays;
		private String remark;
		
		public MedicineAndQuantityModel() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MedicineAndQuantityModel(int medicineId, int medicineQuantity, int noOfDays, int daysInterval,
				int medicineInaDays, String remark) {
			super();
			this.medicineId = medicineId;
			this.medicineQuantity = medicineQuantity;
			this.noOfDays = noOfDays;
			this.daysInterval = daysInterval;
			this.medicineInaDays = medicineInaDays;
			this.remark = remark;
		}

		public int getMedicineId() {
			return medicineId;
		}

		public void setMedicineId(int medicineId) {
			this.medicineId = medicineId;
		}

		public int getMedicineQuantity() {
			return medicineQuantity;
		}

		public void setMedicineQuantity(int medicineQuantity) {
			this.medicineQuantity = medicineQuantity;
		}

		public int getNoOfDays() {
			return noOfDays;
		}

		public void setNoOfDays(int noOfDays) {
			this.noOfDays = noOfDays;
		}

		public int getDaysInterval() {
			return daysInterval;
		}

		public void setDaysInterval(int daysInterval) {
			this.daysInterval = daysInterval;
		}

		public int getMedicineInaDays() {
			return medicineInaDays;
		}

		public void setMedicineInaDays(int medicineInaDays) {
			this.medicineInaDays = medicineInaDays;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		
		
		
		
}
