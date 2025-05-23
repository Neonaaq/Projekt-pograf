#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "podziel_graf.h"

bool poprawka(int **macierz, int *rozmiar, int *czesc, int lwezlow, int lczesci, double min_size, double max_size) {
  bool poprawiony = false;
  for(int w = 0; w < lwezlow; w++){
    int aktualna_czesc = czesc[w];

    //Liczenie obecnej liczby przecietych krawedzi
    int aktualne_przeciecia = 0;
    for(int i = 0; i < lwezlow; i++) {
      if(macierz[w][i] && czesc[i] != aktualna_czesc)
        aktualne_przeciecia++;
    }

    for(int c = 0; c < lczesci; c++){
      if(c == aktualna_czesc) continue;
      if(rozmiar[c] >= max_size || rozmiar[c] <= min_size) continue;

      int nowe_przeciecia = 0;
      for(int i = 0; i < lwezlow; i++){
        if(macierz[w][i] && czesc[i] != c)
          nowe_przeciecia++;
      }

      if(nowe_przeciecia < aktualne_przeciecia){
        rozmiar[aktualna_czesc]--;
        rozmiar[c]++;
        czesc[w] = c;
        poprawiony = true;
        break;
      }
    }
  }
  return poprawiony;
}

void podziel_graf(Komorka *p, int **macierz, int lwezlow, struct Flagi *flaga){
  int lczesci = flaga->division;
  int margines = flaga->hem;

  //TEST
  printf("lwezlow = %d, lczesci = %d\n", lwezlow, lczesci);

  int avg_size = lwezlow / lczesci;
  int max_size = avg_size  * (1 + (margines/100.0));
  int min_size = avg_size  * (1 - (margines/100.0));

  //TEST
  printf("avg = %d, max = %d, min = %d\n",avg_size, max_size, min_size);

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

  bool poprawiono;
  do {
    poprawiono = poprawka(macierz, rozmiar, czesc, lwezlow, lczesci, min_size, max_size);
  } while(poprawiono);

  for(int w = 0; w < lwezlow; w++) {
    p[w].part = czesc[w];
  }
  printf("%d, %d\n", p[93].part, p[94].part);

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
  //TEST
  for (int i =0; i<lczesci; i++) {
    printf("czesc %d = %d\n", i, rozmiar[i]);
  }


  free(czesc);
  free(rozmiar);
}

