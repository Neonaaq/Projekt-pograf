#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "odczytajgraf.h"

int odczytaj_graf(Komorka **p, int ***macierz, FILE *plik) {              // odczytywanie grafu z pliku
    int maxw, lwierszy=0, lwezlow=0, liczba, *T, Tlength=0;
    char znak;
    // Sprawdzanie czy plik jest dobry
    if(plik == NULL || fgetc(plik) == EOF) {
        printf("Błąd NULL: Plik jest pusty. Program przerywa działanie.\n");
        fclose(plik);
        return -1;
    }
    rewind(plik);
    // Zczytywanie kolejnych wierszy 
    while(fscanf(plik,"%d",&liczba) == 1) {
        lwierszy++;
        switch(lwierszy) {
            // pierwszy wiersz - maksymalna liczba węzłów w jednym rzędzie
            case 1:             
                maxw = liczba;
                break;
            // drugi wiersz
            case 2:
                (*p)[lwezlow].column = liczba;
                if(liczba > maxw) {
                    printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                    fclose(plik);
                    return -1;
                }
                znak = fgetc(plik);
                if(znak != ';') { 
                    printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                    fclose(plik);
                    return -1;
                }
                while(fscanf(plik,"%d",&liczba) == 1) {
                    lwezlow++;
                    *p = realloc(*p, (lwezlow+1) * sizeof(Komorka));
                    (*p)[lwezlow].column = liczba;
                    //
                    znak = fgetc(plik);
                    if(znak == '\n') { 
                        break;
                    }
                    else if(znak == ';') {
                        continue;
                    }
                    else {
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                }
                lwezlow++;
                break;
            // trzeci wiersz
            case 3:
                int i=0, row=0, alwezlow=1; //  alwezlow = aktualna liczba wezlow w rzędzie
                (*p)[i].row = row;
                i++;
                znak = fgetc(plik);
                if(znak != ';') { 
                    printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                    fclose(plik);
                    return -1;
                }
                while(fscanf(plik,"%d",&liczba) == 1) {
                    if(i-1>liczba){   // jeżeli ostatnia liczba jest większa niż aktualna liczba
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                    row++;
                    (*p)[liczba].row = row;
                    alwezlow = 1;
                    while(i<liczba) {
                        (*p)[i].row = (*p)[i-1].row;
                        i++;
                        alwezlow++;
                    }
                    if(alwezlow>maxw) {         // jeżeli więcej wezłów w wierszu niż podany max
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                    i = liczba + 1;
                    //
                    znak = fgetc(plik);
                    if(znak == '\n') {
                        break;
                    }
                    else if(znak == ';') {
                        continue;
                    }
                    else {
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                }
                break;
            // czwarty wiersz
            case 4:
                *macierz = malloc((lwezlow+1) * sizeof(int*));
                for(int i=0; i<lwezlow; i++) {
                    (*macierz)[i] = calloc(lwezlow+1, sizeof(int));
                }
                i=0;
                T = malloc(lwezlow * sizeof(int));
                T[i] = liczba;
                i++;
                znak = fgetc(plik);
                if(znak != ';') { 
                    printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                    fclose(plik);
                    return -1;
                }
                while(fscanf(plik,"%d",&liczba) == 1) {
                    T = realloc(T, (i+1) * sizeof(int));
                    T[i] = liczba;
                    i++;
                    //
                    znak = fgetc(plik);
                    if(znak == '\n') {
                        break;
                    }
                    else if(znak == ';') {
                        continue;
                    }
                    else {
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                }
                Tlength = i;
                break;
            // 5+ wiersz
            default:
                int liczba1 = liczba;
                znak = fgetc(plik);
                if(znak != ';') { 
                    printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                    fclose(plik);
                    return -1;
                }
                while(fscanf(plik,"%d",&liczba) == 1) {
                    int liczba2 = liczba;
                    for(int i=liczba1+1; i<liczba2; i++) {
                        (*macierz)[T[liczba1]][T[i]] = 1;
                        // dla grafu nieskierowanego jeszcze:
                        (*macierz)[T[i]][T[liczba1]] = 1;
                    }
                    liczba1 = liczba2;
                    //
                    znak = fgetc(plik);
                    if(znak == EOF ||znak == '\n') {
                        liczba2 = Tlength;
                        for(int i=liczba1+1; i<liczba2; i++) {
                            (*macierz)[T[liczba1]][T[i]] = 1;
                            (*macierz)[T[i]][T[liczba1]] = 1;
                        }
                        break;
                    }
                    else if(znak == ';') {
                        continue;
                    }
                    else {
                        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
                        fclose(plik);
                        return -1;
                    }
                }
                break;
        }
    }
    // Plik musi mieć min. 5 wierszy
    if(lwierszy<5) {
        printf("Błąd FILE: Zły format lub niepoprawne dane. Program przerywa działanie.");
        fclose(plik);
        return -1;
    }
    free(T);
    return lwezlow;
}