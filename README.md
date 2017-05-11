# Unification Algorithm Java
This is an implementation of Unification Algorithm in Java, 
also include converting from any Predicate Calculus (PC) Syntax to LIST Syntax

# Examples
The following examples were tested in the program :
E1 : p(f(a),g(b),Y)
E2 : p(Z,g(w),c)
Output : Fail List
————
E1 : p(f(a),g(b),Y)
E2 : p(Z,g(W),c)
Output :
{c/Y}
{b/W}
{f a/Z}
————
E1 : p(f(f(b)),X)
E2 : p(f(Y),X)
Output : Fail List
————
E1 : p(f(f(b)),c)
E2 : p(f(Y),X)
Output :
{c/X}
{f b/Y}
————
E1 : p(a,X)
E2 : p(b,Y)
Output : Fail List
————
E1 : p(X,a)
E2 : p(b,Y)
Output : 
{a/Y}
{b/X}
————
E1 : f(a,X)
E2 : f(a,b)
Output : {b/X}
————
E1 : f(X)
E2 : f(Y)
Output : {Y/X}
————
E1 : f(g(X))
E2 : f(Y)
Output : {g X/Y}
————
E1 : f(g(X),X)
E2 : f(Y,a)
Output :
{a/X}
{g X/Y}
————
E1 : father(X,Y)
E2 : father(bob,tom)
Output :
{tom/Y}
{bob/X}
————
E1 : parents(X,father(X),mother(bill))
E2 : parents(bill,father(bill),Y)
Output : 
{mother bill/Y}
{bill/X}
————
E1 : grant_parent(X,parent(parent(X)))
E2 : grant_parent(john,parent(Y))
Output :
{parent john/Y}
{john/X}
————
E1 : p(f(a), g(X))
E2 : p(Y,Y)
Output : Fail List
————
E1 : p(a,X, h(g(Z)))
E2 : p(Z, h(Y), h(Y))
Output :
{g a/Y}
{h Y/X}
{a/Z}
————
E1 : p(X,X)
E2 : p(Y,f(Y))
Output : Fail List
————
E1 : part(W ,X)
E2 : connected(f (W ,X), W )
Output : Fail List
————
E1 : p(f(X),a,Y)
E2 : p(f(bill),Z,g(b))
Output : 
{g b/Y}
{a/Z}
{bill/X}
