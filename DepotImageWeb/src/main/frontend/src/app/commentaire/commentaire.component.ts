import { Commentaire, CommentaireService } from './commentaire.service';
import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { CommentaireDto, User } from './commentaire-dto';
import { MenuItem, MessageService } from 'primeng/api';
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
  modifier:boolean;
  commentaire:Commentaire;

  @Input() idDoc: number;
  @Input() idImg: number;
  @Input() type: string;

  iconBtn = 'pi pi-comment';
  classBtn = 'comment-btn p-button-info';
  typeComment = 'simple';
  items: MenuItem[];
  currentUser: CurrentUser;
  commentFG: FormGroup;

  constructor(private service: CommentaireService, private fb: FormBuilder,private loginService: LoginService,private messageService: MessageService) {}

  ngOnInit(): void {
    
    this.modifier=false;
    console.log(this.idDoc);
    console.log(this.type);
    
    this.service.listCommentaire(this.idDoc).subscribe((data:any[])=>{
      this.comments1=data;
      console.log(data);
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
    
    if(this.modifier==false){
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
    console.log(this.commentFG.value);}
    else{

      this.param = pParam;
      this.iconBtn = 'pi pi-comment';
      this.classBtn = 'comment-btn p-button-info';
      this.typeComment = 'simple';
      this.commentFG = this.fb.group({
        comment: [
          
          this.param.commentaire ? this.param.commentaire :this.commentaire.contenu,
          this.param.type === 'ACTION' ? null : Validators.required,
        ],
      });
      console.log(this.commentFG.value);

    }
    this.displayedForm = true;

  }

  deleteComment(a:boolean): void {
    console.log(a);
    if(a==true){
    this.ngOnInit();
    this.messageService.add({severity:'error', summary: 'supprimer', detail: 'commentaire supprimer'});
    }
  
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
      this.messageService.add({severity:'success', summary: 'Success', detail: 'commentaire ajouter'});
      this.ngOnInit();
      
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
   
    this.ngOnInit();
  }

  isInvalidRequired(): boolean {
    return (
      this.commentFG.get('comment').invalid &&
      this.commentFG.get('comment').hasError('required') &&
      (this.commentFG.get('comment').dirty ||
        this.commentFG.get('comment').touched)
    );
  }
  modifierComment( a :Commentaire){
    this.modifier=true;
      console.log(a.contenu);
      this.commentaire=a;
      this.showForm();
      this.displayedForm = true;
      
    
  }
  modifierCommentaire(){
    
   this.commentaire.contenu=this.commentFG.get('comment').value;
   console.log(this.commentaire);
   this.service.modifierCommentaire(this.commentaire).subscribe((data)=>{
     console.log("modification terminer");
     this.messageService.add({severity:'success', summary: 'success', detail: 'commentaire MODIFIER'});
     this.ngOnInit();
   })
  }
}
