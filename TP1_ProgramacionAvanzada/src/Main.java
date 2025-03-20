/*Crear un proyecto en java y resolver el siguiente ejercicio:
    - Generar una serie de 500 números enteros aleatorios entre 10 y 1000
    - Calcular el promedio de estos números así como también la suma total de los mismos.
    - Mostrar el resultado de estos valores obtenidos*/

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int promNum = 0;
        int[] numeros = new int[500];

        // Asignacion de los 500 numeros
        // Suma de los numeros
        for (int i = 0; i < 500; i++) {
            numeros[i] = random.nextInt(1000 - 10) + 10;
            promNum += numeros[i];
        }

        // Mostrar los numeros
        for(int num : numeros) {
            System.out.println(num);

        }

        //Mostrar promedio final
        System.out.print("Promedio Final: " + promNum / 500);

    }


}