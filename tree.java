// them dung de quy

import org.w3c.dom.Node;

Node insert(Node currentNode, Brick value) {
    if (root == null) {
        root = new Node(value);
        return null;
    }

    if (currentNode == null) {
        return new Node(value);
    }
    // if (value.type == currentNode.info.type) {
    // return currentNode;
    // }
    if (value.type > currentNode.info.type) {
        currentNode.right = insert(currentNode.right, value);
    } else {
        currentNode.left = insert(currentNode.left, value);
    }

    return currentNode;
}

// them khong dung de quy
void insert(Ball newBall) {
    Node newNode = new Node(newBall);

    if (root == null) {
        root = newNode;
        return;
    }

    Node parentNode = null;
    Node currentNode = root;

    while (currentNode != null) {
        if (currentNode.info.type == newBall.type) {
            return;
        }

        parentNode = currentNode;

        if (newBall.type < currentNode.info.type) {
            currentNode = currentNode.left;
        } else {
            currentNode = currentNode.right;
        }
    }

    if (newBall.type < parentNode.info.type) {
        parentNode.left = newNode;
    } else {
        parentNode.right = newNode;
    }
}

// xoa node
Node rBST_Delete(Node currentNode, int value) {

    if (currentNode == null) {
        return null;
    }
    if (value == currentNode.info.price) {
        if (currentNode.left == null) {
            return currentNode.right;
        } else {
            if (currentNode.right == null) {
                return currentNode.left;
            }
        }

        // // find minValue in sub right tree
        // Node minNode = findMin(currentNode.right);
        // int minValue = minNode.info.price;
        //// asign minvalue of sub right tree to value of currentNode
        //
        // currentNode.info = minNode.info;
        //// delete node hava minValue in sub right tree
        // currentNode.right = rBST_Delete(currentNode.right, minValue);
        // return currentNode;
        // find max in sub right tree
        Node maxNode = findMax(currentNode.left);
        int maxValue = maxNode.info.price;
        // asign minvalue of sub right tree to value of currentNode

        currentNode.info = maxNode.info;
        // delete node hava minValue in sub right tree
        currentNode.left = rBST_Delete(currentNode.left, maxValue);
        return currentNode;
    }
    if (value > currentNode.info.price) {
        currentNode.right = rBST_Delete(currentNode.right, value);
    } else {
        currentNode.left = rBST_Delete(currentNode.left, value);
    }
    return currentNode;
}

private Node findMin(Node currentNode) {
    while (currentNode.left != null) {
        currentNode = currentNode.left;
    }
    return currentNode;
}

private Node findMax(Node currentNode) {
    while (currentNode.right != null) {
        currentNode = currentNode.right;
    }
    return currentNode;
}

// quay phai

Node findParent(Node child) {
    if (child == root || child == null) {
        return null;
    }

    Node currentNode = root;
    Node parentNode = null;

    while (currentNode != null) {
        if (currentNode.info.price == child.info.price) {
            break;
        }
        parentNode = currentNode;

        if (currentNode.info.price > child.info.price) {
            currentNode = currentNode.left;
        } else {
            currentNode = currentNode.right;
        }
    }

    return parentNode;
}

void rotateRight(Node parent) {
    if (parent == null || parent.left == null) {
        return;
    }

    Node child = parent.left;
    parent.left = child.right;
    child.right = parent;

    Node parentOfParent = findParent(parent);

    if (parentOfParent == null) {
        root = child;
    } else if (parentOfParent.right == parent) {
        parentOfParent.right = child;
    } else {
        parentOfParent.left = child;
    }
}

// quay trái

Node findParent(Node child) {
    if (child == root || child == null) {
        return null;
    }

    Node currentNode = root;
    Node parentNode = null;

    while (currentNode != null) {
        if (currentNode.info.price == child.info.price) {
            break;
        }
        parentNode = currentNode;

        if (currentNode.info.price > child.info.price) {
            currentNode = currentNode.left;
        } else {
            currentNode = currentNode.right;
        }
    }

    return parentNode;
}

void rotateLeft(Node parent) {
    if (parent == null || parent.right == null) {
        return;
    }

    Node child = parent.right;
    parent.right = child.left;
    child.left = parent;

    Node parentOfParent = findParent(parent);

    if (parentOfParent == null) {
        root = child;
    } else if (parentOfParent.left == parent) {
        parentOfParent.left = child;
    } else {
        parentOfParent.right = child;
    }
}