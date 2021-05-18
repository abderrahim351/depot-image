import { Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { CurrentUser, LoginService } from 'src/app/login/login.service';

import { CommentaireDto } from '../commentaire-dto';

@Component({
  selector: 'app-comment-block',
  templateUrl: './comment-block.component.html',
  styleUrls: ['./comment-block.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class CommentBlockComponent implements OnInit {
  @Input() comment: CommentaireDto;

  @Output() modifier = new EventEmitter();

  @Output() supprimer = new EventEmitter();

  currentAuth: CurrentUser;

  constructor(private loginService: LoginService, private confirmationService: ConfirmationService) {}

  ngOnInit(): void {
    this.loginService.currentUserSession().subscribe((u) => {
      console.log(u);
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
    return this.comment.ecritPar.id === this.currentAuth.id;
  }

  showDeleteBtn(): boolean {
    return false;
  }

  actionText(): string {
    switch (this.comment.action) {
      case 'CREATE':
        return 'Création';
      case 'UPDATE':
        return 'Modification';
      case 'UPDATE_SUPPORT':
        return 'Modification des supports';
      case 'DISCHARGE_REQUESTED':
        return 'Changement du statut : déchargement demandé';
    }
    return this.comment.action;
  }

  supprimerButtonAction(): void {
    this.confirmationService.confirm({
      key: 'odn-warn',
      message:
        'Voulez-vous vraiment supprimer ce commentaire ?',
      header: 'Suppression du commentaire ',
      icon: 'pi pi-exclamation-triangle',
      defaultFocus: 'reject',
      accept: () => {
         this.supprimer.emit();
      },
    });
  }
}
