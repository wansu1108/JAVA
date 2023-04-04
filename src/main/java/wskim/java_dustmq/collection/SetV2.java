package wskim.java_dustmq.collection;

import java.util.HashSet;

// Animal Ŭ������ HashSet�� �����ϱ� ���� hashCode()�� equals() �޼ҵ带 �������̵��� ����
public class SetV2 {
	public static void main(String args[]) {
		
		HashSet<Animal> hs = new HashSet<>();
		
		hs.add(new Animal("�����", "����"));
		hs.add(new Animal("�����", "����"));
		hs.add(new Animal("�����", "����"));
		
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
