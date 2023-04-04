package wskim.java_dustmq.collection;

import java.util.HashSet;

// Animal 클래스를 HashSet에 저장하기 위해 hashCode()와 equals() 메소드를 오버라이딩한 예제
public class SetV2 {
	public static void main(String args[]) {
		
		HashSet<Animal> hs = new HashSet<>();
		
		hs.add(new Animal("고양이", "육지"));
		hs.add(new Animal("고양이", "육지"));
		hs.add(new Animal("고양이", "육지"));
		
		System.out.println(hs.size()); // 1
	}
	
	static class Animal {
		
		String species;
		String habitat;
		
		public Animal(String species, String habitat) {
			this.species = species;
			this.habitat = habitat;
		}
		
		public int hashCode() {
			return (species + habitat).hashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof Animal) {
				Animal temp = (Animal) obj;
				return species.equals(temp.species) && habitat.equals(temp.habitat);
			} else {
				return false;
			}
		}
	}
}
