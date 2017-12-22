//http://www.cnblogs.com/daniagger/archive/2012/06/19/2555321.html
struct Node
{
    int val;
    Node* next;
    Node* sibling;
};

void Clone(Node* head)
{
    Node* current=head;
    while(current)
    {
        Node* temp=new Node;
        temp->val=current->val;
        temp->next=current->next;
        temp->sibling=NULL;
        current->next=temp;
        current=temp->next;
    }
}

void ConstructSibling(Node* head)
{
    Node* origin=head;
    Node* clone;
    while(origin)
    {
        clone=origin->next;
        if(origin->sibling)
            clone->sibling=origin->sibling->next;
        origin=clone->next;
    }
}

Node* Split(Node* head)
{
    Node *CloneHead,*clone,*origin;
    origin=head;
    if(origin)
    {
        CloneHead=origin->next;
        origin->next=CloneHead->next;
        origin=CloneHead->next;
        clone=CloneHead;
    }
    while(origin)
    {
        Node* temp=origin->next;
        origin->next=temp->next;
        origin=origin->next;
        clone->next=temp;
        clone=temp;
    }
    return CloneHead;
}

//the whole thing
Clone(head);
ConstructSibling(head);
return Split(head);