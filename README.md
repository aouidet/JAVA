# API Gatherer
Fournir un moyen de particulariser de creer nos propre opérations intermediare
an interface (int fact more than one)
interface Gatherer<T, A, R>


Stream<T> upstream = .....
Gatherer<T, ?, R> gatherer = ....
Stream<R> downstream = upstream.gather(gatherer);

T, corresponsdre aux éléments que produit le stream
R: type des element du stream retournée par gatherer