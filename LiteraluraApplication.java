package com.challengealura.Literalura;

import com.challengealura.Literalura.model.Autor;
import com.challengealura.Literalura.model.Libro;
import com.challengealura.Literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("1 - Buscar libro por título");
			System.out.println("2 - Listar todos los libros");
			System.out.println("3 - Listar autores vivos en un año específico");
			System.out.println("4 - Salir");

			int opcion = 0;
			while (true) {
				try {
					System.out.print("Ingrese su opción: ");
					opcion = scanner.nextInt();
					scanner.nextLine(); // Consume the newline
					break;
				} catch (InputMismatchException e) {
					System.out.println("Por favor, ingrese un número válido.");
					scanner.nextLine(); // Descarta la entrada no válida
				}
			}

			switch (opcion) {
				case 1:
					System.out.println("Ingrese el título del libro:");
					String titulo = scanner.nextLine();
					Libro libro = bookService.buscarLibroPorTitulo(titulo);
					if (libro != null) {
						System.out.println("Libro encontrado: " + libro);
					} else {
						System.out.println("No se encontró ningún libro con ese título.");
					}
					break;
				case 2:
					List<Libro> libros = bookService.obtenerTodosLosLibros();
					System.out.println("Listado de libros:");
					for (Libro l : libros) {
						System.out.println(l);
					}
					break;
				case 3:
					System.out.println("Ingrese el año:");
					int year = 0;
					while (true) {
						try {
							System.out.print("Ingrese el año: ");
							year = scanner.nextInt();
							scanner.nextLine(); // Consume the newline
							break;
						} catch (InputMismatchException e) {
							System.out.println("Por favor, ingrese un número válido.");
							scanner.nextLine(); // Descarta la entrada no válida
						}
					}
					List<Autor> autores = bookService.obtenerAutoresVivosEnYear(year);
					if (!autores.isEmpty()) {
						System.out.println("Autores vivos en " + year + ":");
						for (Autor a : autores) {
							System.out.println(a);
						}
					} else {
						System.out.println("No se encontraron autores vivos en " + year);
					}
					break;
				case 4:
					System.out.println("Saliendo...");
					scanner.close();
					return;
				default:
					System.out.println("Opción no válida.");
			}
		}
	}
}
