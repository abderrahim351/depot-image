import { Commentaire, CommentaireService } from './commentaire.service';
import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { CommentaireDto, User } from './commentaire-dto';
import { MenuItem } from 'primeng/api';
import { CurrentUser, LoginService } from 'src/app/login/login.service';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-commentaire',
  templateUrl: './commentaire.component.html',
  styleUrls: ['./commentaire.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [CommentaireService],
})
export class CommentaireComponent implements OnInit {
  displayedForm = false;
  blockedForm = false;
  blockedLoad = false;
  comments: CommentaireDto[] = [];
  comments1: Commentaire[] = [];
  param: CommentaireDto;

  @Input() idDoc: number;

  iconBtn = 'pi pi-comment';
  classBtn = 'comment-btn p-button-info';
  typeComment = 'simple';
  items: MenuItem[];
  currentUser: CurrentUser;
  commentFG: FormGroup;

  constructor(private service: CommentaireService, private fb: FormBuilder,private loginService: LoginService) {}

  ngOnInit(): void {
    console.log(this.idDoc);
    this.service.listCommentaire(this.idDoc).subscribe((data:any[])=>{
      this.comments1=data;
      console.log(this.comments1.length);
    })
    this.loginService.currentUserSession().subscribe((u) => {
      console.log(u);
      this.currentUser = u;
    });
    this.items = [
      {
        label: 'Simple',
        icon: 'pi pi-comment',
        styleClass: 'SIMPLE',
        command: () => {
          this.iconBtn = 'pi pi-comment';
          this.classBtn = 'comment-btn p-button-info';
          if (this.param.type !== 'ACTION') {
            this.param.type = 'SIMPLE';
          }
        },
      },
      {
        label: 'Alerte',
        icon: 'pi pi-exclamation-triangle',
        styleClass: 'ALERTE',
        command: () => {
          this.iconBtn = 'pi pi-exclamation-triangle';
          this.classBtn = 'comment-btn p-button-warning';
          if (this.param.type !== 'ACTION') {
            this.param.type = 'ALERTE';
          }
        },
      },
      {
        label: 'Critique',
        icon: 'pi pi-exclamation-circle',
        styleClass: 'CRITIQUE',
        command: () => {
          this.iconBtn = 'pi pi-exclamation-circle';
          this.classBtn = 'comment-btn p-button-danger';
          if (this.param.type !== 'ACTION') {
            this.param.type = 'CRITIQUE';
          }
        },
      },
    ];

    this.displayedForm = false;

    //this.load();
  }

  showForm(pParam: CommentaireDto = new CommentaireDto()): void {
    this.param = pParam;
    this.iconBtn = 'pi pi-comment';
    this.classBtn = 'comment-btn p-button-info';
    this.typeComment = 'simple';
    this.commentFG = this.fb.group({
      comment: [
        this.param.commentaire ? this.param.commentaire : '',
        this.param.type === 'ACTION' ? null : Validators.required,
      ],
    });
    this.displayedForm = true;
  }

  deleteComment(id: number): void {
    this.blockedLoad = true;
    this.service.delete(id).then((res) => {
      this.load();
    });
  }

 load(withLoading: boolean = true): void {



    if (withLoading) {
      this.blockedLoad = true;
    } else {
      this.blockedLoad = false;
    }

    this.service.listByIdDemande(this.idDoc).then((res) => {
      this.comments = res;
      this.blockedLoad = false;
    });
  }

  saveComment(): void {
    this.service.ajouterCommentaire({
      creerpar:this.currentUser.id,
      docId :this.idDoc,
      contenu :this.commentFG.get('comment').value,
      type :"commeantaire",
      creerle:null,
      nom:null,
      prenom:null
  
    }).subscribe((data)=>{
      console.log("commentaire ajouter");
      
    })

    if (this.commentFG.valid) {
      this.param.commentaire = this.commentFG.get('comment').value;


      //Temporaire

      this.param.ecritPar = new User();

      this.param.ecritPar.id = 5;
      this.param.ecritPar.nom = 'JAMAAOUI';
      this.param.ecritPar.prenom = 'Aberrahim';
      this.param.date='12/10/2021';
      this.comments.push(this.param);
      this.displayedForm = false;
      this.param = null;

     /* 
      this.blockedForm = true;
      this.service.save(this.idDemande, this.param).then((res) => {
        this.blockedForm = false;
        this.displayedForm = false;
        this.param = null;
        this.load();
      });
     */ 
    } else {
      this.commentFG.get('comment').markAsDirty();
          console.log(this.param.commentaire = this.commentFG.get('comment').value);
    
    }
  }

  onCancel(): void {
    this.blockedForm = false;
    this.displayedForm = false;
    this.param = null;
  }

  isInvalidRequired(): boolean {
    return (
      this.commentFG.get('comment').invalid &&
      this.commentFG.get('comment').hasError('required') &&
      (this.commentFG.get('comment').dirty ||
        this.commentFG.get('comment').touched)
    );
  }
}
