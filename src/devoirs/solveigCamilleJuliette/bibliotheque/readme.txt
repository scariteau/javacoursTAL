Choix de la visibilit� des attributs :
Nous avons choisi de mettre tous les attributs en � public � car nous voulons que les donn�es (auteur, titre�) 
soient accessibles et manipulables dans la classe, dans le package dont elle fait partie, 
mais aussi � l�ext�rieur de ce package.
====> c'est � �a que servent les getter et setter
 
C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\bin>java devoirs.solveigCamilleJuliette.bibliotheque.Catalogue "Vic
tor Hugo"

C:\Users\33616\Documents\Boulot\javacours\javacoursTALCode\bin>java devoirs.solveigCamilleJuliette.bibliotheque.Catalogue "Vic
tor Hugo" "Les Mis�rables



Question 3 : 
Pour ajouter un livre/enlever un livre, nous avons cr�� deux m�thodes addBook() et 
removeBook() (titre et auteur du livre pass�s en param�tres). 
Pour modifier un livre, nous avons cr�� une m�thode modifyBook(), 
qui prend en param�tres le titre et l�auteur du livre � modifier, 
le champ � modifier, et la nouvelle valeur � attribuer � ce champ. 
Pour � raccorder � le champ � modifier au champ indiqu� sous forme de String en param�tre, 
nous avons utilis� un objet Field et la m�thode getField(). 

==>le fichier n'est pas modifi�

