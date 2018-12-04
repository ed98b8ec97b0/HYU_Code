class Type {

}

class VoidType extends Type {
    
}

class IntType extends Type {

}

class BoolType extends Type {

}

class ArrType extends Type {
    int size;
    Type elt;
    ArrType(int size, Type elt) {
        this.size = size;
        this.elt = elt;
    }
}