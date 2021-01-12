Choix de la visibilité des attributs :
Nous avons choisi de mettre tous les attributs en « public » car nous voulons que les données (auteur, titre…) 
soient accessibles et manipulables dans la classe, dans le package dont elle fait partie, 
mais aussi à l’extérieur de ce package.
====> c'est à ça que servent les getter et setter
 
C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\bin>java devoirs.solveigCamilleJuliette.bibliotheque.Catalogue "Vic
tor Hugo"

C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\bin>java devoirs.solveigCamilleJuliette.bibliotheque.Catalogue "Vic
tor Hugo" "Les Misérables



Question 3 : 
Pour ajouter un livre/enlever un livre, nous avons créé deux méthodes addBook() et 
removeBook() (titre et auteur du livre passés en paramètres). 
Pour modifier un livre, nous avons créé une méthode modifyBook(), 
qui prend en paramètres le titre et l’auteur du livre à modifier, 
le champ à modifier, et la nouvelle valeur à attribuer à ce champ. 
Pour « raccorder » le champ à modifier au champ indiqué sous forme de String en paramètre, 
nous avons utilisé un objet Field et la méthode getField(). 

==>le fichier n'est pas modifié

