#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const int HASH_SEED = 19;

struct hashnode {
  char * str;
  struct hashnode * next;
};

// hashtable that stores strings
struct hashtable {
  struct hashnode ** table;
  int size;
};


// compute a hash of a string using a seed value, where the result
// falls between zero and range-1
int hash_string(char * string, int seed, int range)
{
  int i;
  int hash = 0;

  // simple loop for mixing the input string
  for ( i = 0; string[i] != '\0'; i++ ) {
    hash = hash * seed + string[i];
  }
  // check for unlikely case that hash is negative
  if ( hash < 0 ) {
    hash = -hash;
  }
  // bring the hash within the range 0..range-1
  hash = hash % range;
  
  return hash;
}

// create a new empty hashtable
struct hashtable * hashtable_new(int size)
{
  struct hashtable * result;
  int i;
  
  result = malloc(sizeof(struct hashtable));
  result->size = size;
  result->table = malloc(sizeof(struct hashnode *) * size);
  for ( i = 0; i < size; i++ ) {
    result->table[i] = NULL;
  }
  return result;
}

// add an item to the hashtable
void hashtable_insert(struct hashtable * this, char * item)
{
  int index;
  struct hashnode * node;
  
  // make a copy of the string we are inserting
  char * copy;
  int length = strlen(item);
  copy = malloc(sizeof(char)*(length+1));
  strcpy(copy, item);

  // find the right location in the hash table
  index = hash_string(item, HASH_SEED, this->size);

  // insert a new node containing item
  node = malloc(sizeof(struct hashnode));
  node->str = copy;
  node->next = this->table[index];
  this->table[index] = node;
}

// return 1 if item is in hashtable, 0 otherwise
int hashtable_lookup(struct hashtable * this, char * item)
{
  struct hashnode * current;
  int index;

  index = hash_string(item, HASH_SEED, this->size);

  for (current = this->table[index]; current != NULL; current = current->next) {
    // if the string are equal
    if ( strcmp(current->str, item) == 0 ) {
      return 1;
    }
  }
  return 0;
}

// remove an item from the hash table; if the item is in the table
// multiple times, just remove the first one that we encounter
void hashtable_remove(struct hashtable * this, char * item)
{
  struct hashnode * current;
  struct hashnode * prev;
  int index;

  // list at index is empty?
  index = hash_string(item, HASH_SEED, this->size);
  if ( this->table[index] == NULL ) {
    return;
  }
  // first item in list is what we are looking for?
  else if ( strcmp(this->table[index]->str, item) == 0 ) {
    current = this->table[index];
    this->table[index] = this->table[index]->next;
    free(current->str);
    free(current);
    return;
  }
  // search the rest of the list for the item
  prev = this->table[index];
  current = prev->next;
  while (current != NULL ) {
    if ( strcmp(current->str, item) == 0 ) {
      prev->next = current->next;
      free(current->str);
      free(current);
      return;
    }
    prev = current;
    current = current->next;
  }
}


int main()
{
  struct hashtable * mytable;
  int result;

  mytable = hashtable_new(100);
  hashtable_insert(mytable, "Wish I could turn back time");
  result = hashtable_lookup(mytable, "Wish I could turn back time");
  fprintf(stderr, "%d\n", result);
  
  hashtable_remove(mytable, "Wish I could turn back time");
  result = hashtable_lookup(mytable, "Wish I could turn back time");
  
  fprintf(stderr, "%d\n", result);
  
  return 0;
}
