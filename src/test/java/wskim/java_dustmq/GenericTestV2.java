package wskim.java_dustmq;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GenericTestV2 {
	
	class LandAnimal { public void crying() { System.out.println("��������"); } }
	class Cat extends LandAnimal { public void crying() { System.out.println("�Ŀ˳Ŀ�"); } }
	class Dog extends LandAnimal { public void crying() { System.out.println("�۸�"); } }
	class Sparrow { public void crying() { System.out.println("±±"); } }
	
	static class AnimalList<T> {
	    ArrayList<T> al = new ArrayList<T>();
	    
	    public static void cryingAnimalList(AnimalList<? extends LandAnimal> al) {
	        LandAnimal la = al.get(0);
	        la.crying();
	    }

	    void add(T animal) { al.add(animal); }
	    T get(int index) { return al.get(index); }
	    boolean remove(T animal) { return al.remove(animal); }
	    int size() { return al.size(); }
	}
	
	@Test
	public void genericV2() {
		AnimalList<Cat> catList = new AnimalList<>();
		catList.add(new Cat());
		AnimalList<Dog> dogList = new AnimalList<>();
		dogList.add(new Dog());
		
		AnimalList.cryingAnimalList(catList);
		AnimalList.cryingAnimalList(dogList);
	}

}
