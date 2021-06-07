package com.sdi.dimage.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sdi.dimage.dao.entities.AbstractUtilisateurEntity;
import com.sdi.dimage.dao.entities.DocumentEntity;
import com.sdi.dimage.dao.entities.ImageEntity;
import com.sdi.dimage.dao.entities.ImageMetadataEntity;
import com.sdi.dimage.dao.repositories.DocumentRepositery;
import com.sdi.dimage.dao.repositories.ImageMetadataRepositery;
import com.sdi.dimage.dao.repositories.ImageRepository;
import com.sdi.dimage.dao.repositories.UtlisateurRepository;
import com.sdi.dimage.utils.DocImgDetailsModel;
import com.sdi.dimage.utils.DocImgModel;
import com.sdi.dimage.utils.DocumentModel;
import com.sdi.dimage.utils.MetaTreeNodeModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepo;
	@Autowired
	private DocumentRepositery documentRepositery;
	@Autowired
	private UtlisateurRepository utlisateurRepository;
	@Autowired
	private ImageMetadataRepositery metarepository;


	//liste des metadata
	public List<MetaTreeNodeModel> metadatas(int idimg) {
		List<ImageMetadataEntity> listMestaEntities =  metarepository.listMetadata(idimg);
		
		
		List<MetaTreeNodeModel> listMetaModels = new ArrayList<>();
		
		MetaTreeNodeModel gmmTmp = null;
		for (ImageMetadataEntity ime : listMestaEntities) {
			
			if (gmmTmp==null || !ime.getDirectory().equals(gmmTmp.getData().getDirectory())) {
				gmmTmp = new MetaTreeNodeModel(ime.getDirectory());
				listMetaModels.add(gmmTmp);
			}
			gmmTmp.getChildren().add(new MetaTreeNodeModel(ime.getTag(), ime.getValeur()));
			
		}
		
		return listMetaModels;

	}

	//liste des images
	public List<ImageEntity> getphoto() {
		return this.imageRepo.findAll();

	}

	public List<DocImgModel> documents() {
		
		List<DocumentEntity> documents = documentRepositery.findAll();
		
		ArrayList<DocImgModel> listModels =new ArrayList<>(documents.size()); 
		
		for (DocumentEntity de : documents) {
			DocImgModel aux =new DocImgModel();
			aux.setNom(de.getCreePar().getNom());
			aux.setPrenom(de.getCreePar().getPrenoms());
			
			aux.setTitre(de.getTitre());
			aux.setSoustitre(de.getSousTitre());
			
			aux.setDescription(de.getDescription());
			aux.setIdDoc(de.getId());
			aux.setType(de.getType());
			listModels.add(aux);
		}
		
		return listModels;
	}

	public Integer enregistrerdoc(DocumentModel document,
			UtilisateurSessionDto user) {

		DocumentEntity doc = new DocumentEntity();
		doc.setTitre(document.getTitre());
		doc.setSousTitre(document.getSousTitre());
		doc.setDescription(document.getDescription());
		doc.setEstPublique(document.getPublique());
		doc.setType(document.getType());
		System.out.print(document.getPublique());
		doc.setStatut(null);
		//doc.setImages(null);
		//doc.setImagePrincipal(null);

		doc.setCreePar(utlisateurRepository.getOne(user.getId()));
		doc.setCommentaires(null);
		doc.setCreeLe(LocalDateTime.now());

		documentRepositery.save(doc);
		return doc.getId();

	}

	@Transactional
	public void uplodfile(Integer idDoc, List<MultipartFile> files,
			UtilisateurSessionDto userSession) throws IOException {

		DocumentEntity doc = documentRepositery.getOne(idDoc);
		AbstractUtilisateurEntity user = utlisateurRepository
				.getOne(userSession.getId());

		LocalDateTime now = LocalDateTime.now();

		List<ImageEntity> imageEntities = new ArrayList<>(files.size());

		for (int i = 0; i < files.size(); i++) {
			MultipartFile mf = files.get(i);
			ImageEntity img = new ImageEntity();
			img.setDocument(doc);
			img.setContenu(mf.getBytes());
			img.setTailleFichier(mf.getSize());
			img.setTypeFichier(mf.getContentType());

			img.setCreePar(user);
			img.setCreeLe(now);

			try {
				Metadata metadata = ImageMetadataReader
						.readMetadata(new ByteArrayInputStream(mf.getBytes()));

				List<ImageMetadataEntity> metas = new ArrayList<>();

				for (Directory directory : metadata.getDirectories()) {

					String directoryName = directory.getName();

					for (Tag tag : directory.getTags()) {
						ImageMetadataEntity meta = new ImageMetadataEntity();
						meta.setImage(img);
						meta.setDirectory(directoryName);
						meta.setTag(tag.getTagName());
						meta.setValeur(tag.getDescription());

						metas.add(meta);

					}
				}

				if (!metas.isEmpty()) {
					metarepository.saveAll(metas);
				}

			} catch (ImageProcessingException | IOException e) {

				e.printStackTrace();
			}

			imageEntities.add(img);

		}

		imageRepo.saveAll(imageEntities);

		doc.setImagePrincipal(imageEntities.get(0));

		documentRepositery.save(doc);

	}

	public ImageEntity getImagePrincipale(Integer idDoc) {

		return documentRepositery.getOne(idDoc).getImagePrincipal();
	}

	public void supprimerdoc(int id) {
		this.documentRepositery.deleteById(id);

	}

	//
	public DocImgDetailsModel detailePub(int id) {
		DocImgDetailsModel detaille = new DocImgDetailsModel();

		DocumentEntity doc = this.documentRepositery.getOne(id);

		detaille.setNom(doc.getCreePar().getNom());
		detaille.setPrenom(doc.getCreePar().getPrenoms());
		detaille.setDescription(doc.getDescription());
		detaille.setTitre(doc.getTitre());
		detaille.setSoustitre(doc.getSousTitre());
		detaille.setCreeLe(doc.getCreeLe());

		detaille.setIdsImage(imageRepo.listImagesParDoc(id));

		return detaille;

	}

	public ImageEntity getImage(Integer idImg) {

		return this.imageRepo.getOne(idImg);
	}

	public void listeImage(int iddoc) {
		List<Integer> imageId = new ArrayList<>();

		for (ImageEntity img : this.getphoto()) {

			if (img.getDocument().getId().equals(iddoc)) {
				imageId.add(img.getId());
			}

		}

	}

}
