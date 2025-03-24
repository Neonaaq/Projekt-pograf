#ifndef ARGS_H
#define ARGS_H

enum FileMode { TEXT, BINARY };

struct Flagi {
	int division;
	int hem;
	enum FileMode file_mode;
	const char *output_filename;
};

int parse_args(int argc, char *argv[], struct Flagi *flaga, int lwezlow);

#endif