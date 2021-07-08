import { Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CurrentUser, LoginService } from 'src/app/login/login.service';

import { CommentaireService } from '../commentaire.service';
import { Commentaire } from '../commentaire.service';

@Component({
  selector: 'app-comment-block',
  templateUrl: './comment-block.component.html',
  styleUrls: ['./comment-block.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class CommentBlockComponent implements OnInit {
  @Input() comment: Commentaire;

  @Output() modifier = new EventEmitter();

  @Output() supprimer = new EventEmitter();

  currentAuth: CurrentUser;

  constructor(private messageService: MessageService , private loginService: LoginService, private confirmationService: ConfirmationService,private CommentaireService : CommentaireService ) {}

  ngOnInit(): void {
    this.loginService.currentUserSession().subscribe((u) => {
     
      this.currentAuth = u;
    });
  }

  severity(): string {
    if (this.comment && this.comment.type) {
      switch (this.comment.type) {
        case 'ALERTE':
          return 'warn';
        case 'CRITIQUE':
          return 'error';
        case 'ACTION':
          return 'success';
      }
    }
    return 'info';
  }

  showAction(): boolean {
    return this.comment && this.comment.type === 'ACTION';
  }

  showEditBtn(): boolean {
    return this.comment.creerpar === this.currentAuth.id;
  }

  showDeleteBtn(): boolean {
    return this.comment.creerpar === this.currentAuth.id;
  }

  actionText(): string {
    switch (this.comment.type) {
      case 'CREATE':
        return 'Création';
      case 'UPDATE':
        return 'Modification';
      case 'UPDATE_SUPPORT':
        return 'Modification des supports';
      case 'DISCHARGE_REQUESTED':
        return 'Changement du statut : déchargement demandé';
    }
    return this.comment.type;
  }

  supprimerButtonAction(id : number): void {

    console.log("test")
    this.confirmationService.confirm({
      message: 'supprimer ce commentaire',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
      this.CommentaireService.supprimerCommentaire(id).subscribe((data)=>{
        this.supprimer.emit(true);
      })
     }})}
  showConfirm() {
    
    
}
modifierCommentaire(a : Commentaire): void{
console.log(a.contenu);
this.modifier.emit(a.contenu);


}
}
