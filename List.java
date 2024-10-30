
void addLast(Car x) {// You should write here appropriate statements to complete this function.
    Node q = new Node(x);
    if (isEmpty()) {
        head = tail = q;
    } else {
        tail.next = q;
        tail = q;
    }
}

void addFirst(Car x) {
    Node p = new Node(x);
    if (isEmpty()) {
        head = tail = p;
        return;
    }
    p.next = head;
    head = p;
}

void addAfter(Node currentNode, Car x) {
    Node nodeInsert = new Node(x);
    if (isEmpty()) {
        return;
    }
    nodeInsert.next = currentNode.next;
    currentNode.next = nodeInsert;
    if (currentNode == tail) {
        tail = nodeInsert;
    }
}

void insert(Castor x, int index) {
    int count = 0;
    Node p = head;
    while (p.next != null) {
        if (index == 0) {
            this.addFirst(x);
            break;
        }
        if (count == index - 1) {
            this.addAfter(p, x);
            break;
        }
        count++;
        p = p.next;
    }
}

// xoa
void delete(Node nodeDelete) {
    Node beforeNode, currentNode;
    beforeNode = null;
    currentNode = head;
    while (currentNode != null) {
        if (currentNode == nodeDelete) {
            break;
        }
        beforeNode = currentNode;
        currentNode = currentNode.next;
    }
    if (currentNode == null) {
        return; // not found Node delete
    }

    if (beforeNode == null) {
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return;
    }

    beforeNode.next = currentNode.next;
    if (beforeNode.next == null) {
        tail = beforeNode;
    }
}
// xoa theo gia tri

void delete(int xPrice) {
    Node currentNode = head;
    while (currentNode != null) {
        if (currentNode.info.price == xPrice) {
            break;
        }
        currentNode = currentNode.next;
    }
    if (currentNode != null) {
        delete(currentNode);
    }
}

// sort

void sort(int startIndex, int endIndex) {
    int currentIndex = 0, sortedCount = 0;
    Car tempCar;
    Node currentNode = head, innerNode;

    while (currentNode.next != null) {
        if (currentIndex == startIndex) {
            for (; currentNode != null; currentNode = currentNode.next) {
                int innerCount = 0;

                for (innerNode = currentNode.next; innerNode != null; innerNode = innerNode.next) {
                    if (currentNode.info.price > innerNode.info.price) {
                        // Swap the cars
                        tempCar = currentNode.info;
                        currentNode.info = innerNode.info;
                        innerNode.info = tempCar;
                    }

                    innerCount++;
                    if (sortedCount + innerCount == endIndex - startIndex) {
                        break;
                    }
                }

                if (sortedCount + 1 == endIndex - startIndex) {
                    break;
                }

                sortedCount++;
            }
            break;
        }

        currentIndex++;
        currentNode = currentNode.next;
    }
}

int indexLast() {
    int index1 = 0;
    Node currentNode = head;
    while (currentNode.next != null) {
        index1++;
        currentNode = currentNode.next;
    }
    return index1;
}

// cach dung : sort(0, indexLast());