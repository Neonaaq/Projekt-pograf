#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "podziel_graf.h"

int liczenie_krawedzi(int lwezlow, int **macierz, int *czesc) {
  int aktualne_krawedzie = 0;
  for (int i=0; i<lwezlow; i++) {
    for (int j=0; j<lwezlow; j++) {
      if (macierz[i][j] == 1 && j>i && czesc[i] == czesc[j])
        aktualne_krawedzie++;
    }
  }
  return aktualne_krawedzie;
}

bool poprawka(int **macierz, int *rozmiar, int *czesc, int lwezlow, int lczesci, double min_size, double max_size) {
  bool poprawiony = false;

  //Liczenie obecnej liczby krawedzi
  int krawedzie1 = liczenie_krawedzi(lwezlow, macierz, czesc);

  for (int w = 0; w < lwezlow; w++) {
    int aktualna_czesc =  czesc[w];

    if (rozmiar[aktualna_czesc] > min_size) {
      for (int c = 0; c < lczesci; c++) {
      if (c == aktualna_czesc || rozmiar[c]>=max_size) continue;
      czesc[w] = c;
      int krawedzie2 = liczenie_krawedzi(lwezlow, macierz, czesc);
      if (krawedzie2 > krawedzie1) {
        rozmiar[c]++;
        rozmiar[aktualna_czesc]--;
        poprawiony = true;
        break;
      }
      else {
        czesc[w] = aktualna_czesc;
      }
      }
    }
  }

  return poprawiony;
}

void podziel_graf(Komorka *p, int **macierz, int lwezlow, struct Flagi *flaga){
  int lczesci = flaga->division;
  int margines = flaga->hem;

  int avg_size = lwezlow / lczesci;
  int max_size = avg_size  * (1 + (margines/100.0));
  int min_size = avg_size  * (1 - (margines/100.0));

  int *czesc = malloc(lwezlow * sizeof(int));
  int *rozmiar = calloc(lczesci, sizeof(int));

  //zachlanne przypisanie wezlow do czesci
  for(int w = 0; w < lwezlow; w++) {
    int random_czesc = rand() % lczesci;
    while(rozmiar[random_czesc] >= max_size){
      random_czesc = rand() % lczesci;
    }
    czesc[w] = random_czesc;
    rozmiar[random_czesc]++;
  }

  for (int i=0; i<lczesci; i++) {
    while (rozmiar[i]<min_size) {
      for (int j=0; j<lczesci; j++) {
        if (j != i && rozmiar[j] > min_size) {
          int random_wezel = rand() % lwezlow;
          while (czesc[random_wezel] != j) {
            random_wezel = rand() % lwezlow;
          }
          rozmiar[j]--;
          rozmiar[i]++;
          czesc[random_wezel] = i;
        }
      }
    }
  }


  bool poprawiono;
  do {
    poprawiono = poprawka(macierz, rozmiar, czesc, lwezlow, lczesci, min_size, max_size);
  } while(poprawiono);

  for(int w = 0; w < lwezlow; w++) {
    p[w].part = czesc[w];
  }

  //zniszczenie krawedzi jesli wezly sa w innych czesciach
  for(int i=0; i<lwezlow; i++) {
    for(int j=0; j<lwezlow; j++) {
      if(i == j) continue;
      if(p[i].part != p[j].part) {
        macierz[i][j] = 0;
        macierz[j][i] = 0;
      }
    }
  }

  free(czesc);
  free(rozmiar);
}

