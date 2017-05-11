# Unification Algorithm Java
This is an implementation of Unification Algorithm in Java, <br />
also include converting from any Predicate Calculus (PC) Syntax to LIST Syntax<br />

# Examples
The following examples were tested in the program :<br />
E1 : p(f(a),g(b),Y)<br />
E2 : p(Z,g(w),c)<br />
Output : Fail List<br />
————<br />
E1 : p(f(a),g(b),Y)<br />
E2 : p(Z,g(W),c)<br />
Output :<br />
{c/Y}<br />
{b/W}<br />
{f a/Z}<br />
————<br />
E1 : p(f(f(b)),X)<br />
E2 : p(f(Y),X)<br />
Output : Fail List<br />
————<br />
E1 : p(f(f(b)),c)<br />
E2 : p(f(Y),X)<br />
Output :<br />
{c/X}<br />
{f b/Y}<br />
————<br />
E1 : p(a,X)<br />
E2 : p(b,Y)<br />
Output : Fail List<br />
————<br />
E1 : p(X,a)<br />
E2 : p(b,Y)<br />
Output : <br />
{a/Y}<br />
{b/X}<br />
————<br />
E1 : f(a,X)<br />
E2 : f(a,b)<br />
Output : {b/X}<br />
————<br />
E1 : f(X)<br />
E2 : f(Y)<br />
Output : {Y/X}<br />
————<br />
E1 : f(g(X))<br />
E2 : f(Y)<br />
Output : {g X/Y}<br />
————<br />
E1 : f(g(X),X)<br />
E2 : f(Y,a)<br />
Output :<br />
{a/X}<br />
{g X/Y}<br />
————<br />
E1 : father(X,Y)<br />
E2 : father(bob,tom)<br />
Output :<br />
{tom/Y}<br />
{bob/X}<br />
————<br />
E1 : parents(X,father(X),mother(bill))<br />
E2 : parents(bill,father(bill),Y)<br />
Output : <br />
{mother bill/Y}<br />
{bill/X}<br />
————<br />
E1 : grant_parent(X,parent(parent(X)))<br />
E2 : grant_parent(john,parent(Y))<br />
Output :<br />
{parent john/Y}<br />
{john/X}<br />
————<br />
E1 : p(f(a), g(X))<br />
E2 : p(Y,Y)<br />
Output : Fail List<br />
————<br />
E1 : p(a,X, h(g(Z)))<br />
E2 : p(Z, h(Y), h(Y))<br />
Output :<br />
{g a/Y}<br />
{h Y/X}<br />
{a/Z}<br />
————<br />
E1 : p(X,X)<br />
E2 : p(Y,f(Y))<br />
Output : Fail List<br />
————<br />
E1 : part(W ,X)<br />
E2 : connected(f (W ,X), W )<br />
Output : Fail List<br />
————<br />
E1 : p(f(X),a,Y)<br />
E2 : p(f(bill),Z,g(b))<br />
Output : <br />
{g b/Y}<br />
{a/Z}<br />
{bill/X}<br />
