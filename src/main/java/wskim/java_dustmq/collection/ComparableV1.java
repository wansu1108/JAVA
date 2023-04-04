package wskim.java_dustmq.collection;

public class ComparableV1 {
	
	public static void main(String[] args) {
		Car car01 = new Car("아반떼" , 2016, "노란색");
		Car car02 = new Car("소나타" , 2010, "흰색");
		
		System.out.println(car01.compareTo(car02)); // 1
	}
	
	static class Car implements Comparable<Car> {
		
		private String modelName;
		private int modelYear;
		private String color;
		
		public Car(String modelName, int modelYear, String color) {
			this.modelName = modelName;
			this.modelYear = modelYear;
			this.color = color;
		}
		
		public String getModel() {
	        return this.modelYear + "식 " + this.modelName + " " + this.color;
	    }

		@Override
		public int compareTo(Car o) {
			if(this.modelYear == o.modelYear) {
				return 0;
			} else if (this.modelYear < o.modelYear) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
