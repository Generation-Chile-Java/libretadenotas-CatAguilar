package libreta;

import java.util.*;

public class LibretaDeNotas {
    private static Map<String, List <Double>> libroCorte = new HashMap<>();
    private static final double NotaAprovacion = 4.0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al menú del libro de notas virtual");
        System.out.println("¿Desea ingresar un libro de clases? (S/N)");
        char respuesta;
        respuesta = sc.next().charAt(0);

        if (respuesta == 'S' || respuesta == 's') {
            ingresarDatos(sc);

            mostrarMenu(sc);

        }else {
            System.out.println("Vuelve a intentarlo después");
        }

    }//Llave del main

    private static void ingresarDatos(Scanner sc) {

            System.out.println("Ingrese la cantidad de alumnos de la corte: ");
            int cantidadAlumnos = sc.nextInt();

            //Recorrer el map despues de limitar la cantidad de alumnos keys que se pueden ingresar
            for (int i = 0; i < cantidadAlumnos; i++) {
                System.out.println("Ingrese el nombre del almuno: ");
                String nombreAlumno = sc.next();

                System.out.println("Cuantas notas tiene " + nombreAlumno + " ?");
                int notaAlumno = sc.nextInt();

                //Recorrer la lista de notas de cada alumno key despues de limitar la cantidad de notas que se va a ingresar
                List <Double> notasAlumnos = new ArrayList<>();
                for (double j = 0; j < notaAlumno; j++) {
                    System.out.println("Ingrese la nota " + (j + 1) + " del almuno " + nombreAlumno + " en formato 0,0:");
                    double nota = sc.nextDouble();
                    notasAlumnos.add(nota);
                }sc.nextLine();

                libroCorte.put(nombreAlumno, notasAlumnos);
            }
            System.out.println("Notas ingresadas correctamente al libro de la corte");
        System.out.println(" ");
        imprimeLibroCorte();
        System.out.println(" ");



    }//Llave de Ingresar datos

    private static void mostrarMenu(Scanner sc) {
        char respuesta2;

        do {
            System.out.println(" ");
            System.out.println("¿Qué acciones complementarias deseas hacer con el libro de la corte? ");
            System.out.println(" ");
            System.out.println("Opciones:" +
                    "\n(1)Mostrar el Promedio de Notas por Estudiante +" +
                    "\n(2)Mostrar la nota minima y máxima de cada estudiante" +
                    "\n(3)Mostrar si una Nota es Aprobatoria o Reprobatoria por Estudiante" +
                    "\n(4)Mostrar si una Nota está por Sobre o por Debajo del Promedio del Curso" +
                    "\n(0)Ninguna");
            System.out.println("Selecciones un número o marque 0 para salir");
            respuesta2 = sc.next().charAt(0);

            switch (respuesta2) {

                case '1':
                    imprimePromediosAlumnos(sc);
                    break;

                case '2':

                    notaMenorYMayor(sc);
                    break;

                case '3':

                    notaAprobatoriaONo(sc);
                    break;

                case '4':

                    notaSobrePromedioONo(sc);
                    break;
                    default:
                        System.out.println("Opcion no valida");
            }
        }while(respuesta2 != '0');


    }//Llave de mostrarMenu


    //Metodo para imprimir el map
    private static void imprimeLibroCorte() {
        for (Map.Entry<String, List <Double>> claveValor : libroCorte.entrySet()) {
            System.out.println(claveValor.getKey() + ": " + claveValor.getValue());
        }
    }

    private static void imprimePromediosAlumnos(Scanner sc) {
        System.out.println("Los promedios de cada alumno en el libro de la corte son: ");
        for (Map.Entry<String, List <Double>> claveValor : libroCorte.entrySet()) {
            String nombre = claveValor.getKey();
            List<Double> notas = claveValor.getValue();

            double suma = 0.0;
            for (double nota : notas) {
                suma += nota;

            } double promedio = suma / notas.size();
            System.out.println("Alumno " + nombre + " tiene promedio: " + promedio);
        }
        //sumar las notas de cada alumno y dividirlas por la notasAlumnos
    }

    private static void notaMenorYMayor(Scanner sc) {
        System.out.println("\nNota Máxima y Mínima por Estudiante:");
        for (Map.Entry<String, List<Double>> entry : libroCorte.entrySet()) {
            double max = Collections.max(entry.getValue());
            double min = Collections.min(entry.getValue());
            System.out.printf("%s - Máxima: %.2f | Mínima: %.2f%n", entry.getKey(), max, min);
        }
    }


    private static void notaAprobatoriaONo(Scanner sc) {
        sc.nextLine();
        System.out.println("Ingresa el nombre del alumno: ");
        String consultaAlumno = sc.nextLine();


        if (libroCorte.containsKey(consultaAlumno)) {
            System.out.println("Ingrese la nota ");
            double nota = sc.nextDouble();
            List<Double> notas = libroCorte.get(consultaAlumno);
            libroCorte.get(consultaAlumno);

            if (nota < 1.0 || nota > 7.0) {
                System.out.println("Nota inválida en el sistema chileno");
                return;
            }

            if (nota < NotaAprovacion) {
                System.out.println("El alumno sacó una nota reprobatoria, llamar apoderado");
            } else System.out.println("El alumno saco una nota aprobatoria, todo bien");

        }else System.out.println("El alumno no existe en este libro de corte");


    }
    private static void notaSobrePromedioONo(Scanner sc) {
        System.out.println("Ingresa la nota a comparar con el promedio general de la corte");

        double nota = sc.nextDouble();
        double sumaTotal = 0.0;
        int cantidadNotas = 0;

        for (List<Double> notasAlumno : libroCorte.values()) {
            for (double n : notasAlumno) {
                sumaTotal += n;
                cantidadNotas++;
            }
        }
        double promedioCurso = (cantidadNotas > 0) ? (sumaTotal / cantidadNotas) : 0.0;

        if (nota > promedioCurso) {
            System.out.println("La nota está por sobre el promedio del curso.");
        } else {
            System.out.println("La nota está por debajo del promedio del curso.");
        }
    }





}//Llave de la clase libretadenotas








