export class User {
  id: number;
  nom: string;
  prenom: string;
  login: string;
  groupe: string;
}


export class CommentaireDto {
  id: number;
  commentaire: string;
  type = 'SIMPLE';
  date: string;
  action: string;
  ecritPar: User;
}
