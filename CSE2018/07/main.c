#include "hanoi.h"

int cnt = 0;

int main(void) {
	int n;
	n = get_n_from_user();
	assert(n > 0);
	move(n, 'A', 'B', 'C');

	return 0;
}
