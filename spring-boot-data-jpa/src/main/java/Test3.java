
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Test3 {

	public static void main(String[] args) {
		
		List<Television> listaTelevision;

		listaTelevision = Arrays.asList(new Television(1,"Sony"),new Television(5,"LG"),new Television(3,"TLC"),new Television(9,"Philips"));
		
		//Television t = listaTelevision.stream().filter(elemento->(elemento.id==9)).findFirst().get();
		
		
		listaTelevision.stream().sorted((a,b)->a.marca.compareToIgnoreCase(b.marca)).distinct().forEach(a->System.out.println(a.marca));
		
		//System.out.println(t.marca);
		
		/*
		Collections.sort(listaTelevision,((Television a,Television b)->a.id<b.id?-1:1));
		
		listaTelevision.forEach(a->System.out.println(a.id+"-"+a.marca));
		
		Collections.sort(listaTelevision,((Television a,Television b)->a.marca.compareTo(b.marca)));
		
		listaTelevision.forEach(a->System.out.println(a.id+"-"+a.marca));
		*/
		
		List<String> listaNombres = Arrays.asList("Luis","Juan","Pedro","Aysha","Trinidad","Dario");
		String resultado = listaNombres.stream().sorted((a,b)->a.compareTo(b)).filter(a->a.equalsIgnoreCase("Pedro")).findFirst().toString();
	}
}

class Television {
	int id;
	String marca;
	
	public Television(int id,String marca) {
		this.id = id;
		this.marca = marca;
	}
	
}