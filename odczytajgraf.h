#ifndef ODCZYTAJGRAF_H
#define ODCZYTAJGRAF_H

#include <stdio.h>

typedef struct {        // Lokalizacja danej komórki - rząd i kolumna
    int row;
    int column;
    int part;
} Komorka;

int odczytaj_graf(Komorka **p, int ***macierz, FILE *plik);

#endif
