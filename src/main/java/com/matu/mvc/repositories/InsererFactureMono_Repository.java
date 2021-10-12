package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.matu.mvc.models.InsererFactureMono;
import com.matu.mvc.models.Nb_Formules;
import com.matu.mvc.models.StdQuittances;

public interface InsererFactureMono_Repository extends JpaRepository<InsererFactureMono,Integer > {
	

	 @Modifying
	 @Query(value = "declare @Msg_ varchar(500) ,@NomFac varchar(15)\r\n" + 
	 		"exec [dbo].[ps_MatuFac_InsererFactureMono] :idsite, :idclient,:exercice,'D',:quittances,:idoperateur,@NomFac  output,  @Msg_  output \r\n" + 
	 		"print @NomFac", nativeQuery = true)
	    @Transactional
	    void insererfacturemono(@Param ("idsite") Integer  idsite,@Param ("idclient") Integer  idclient,@Param ("exercice") String  exercice,@Param ("quittances") String  quittances,@Param ("idoperateur") Long  idoperateur);
	 
	 // save facture _rd ***
	 @Modifying
	 @Query(value = "declare @Msg_ varchar(500) ,@NomFac varchar(15)\r\n" + 
	 		"exec [dbo].[ps_MatuFac_InsererFactureRD] :idsite, :idclient,:exercice,'D',:quittances,:idoperateur,@NomFac  output,  @Msg_  output \r\n" + 
	 		"print @NomFac", nativeQuery = true)
	    @Transactional
	    void insererfacturerd(@Param ("idsite") Integer  idsite,@Param ("idclient") Integer  idclient,@Param ("exercice") String  exercice,@Param ("quittances") String  quittances,@Param ("idoperateur") Long  idoperateur);
	 
       	 
	 @Query(nativeQuery = true, value = " declare @NumFacture varchar(15),@CodeSite varchar(3),@Ann varchar(4),@TypeFacture varchar(4) \r\n" + 
	 		"	 		declare @CptFac int,@idclient int \r\n" + 
	 		"	 		set @Ann=:exercice \r\n" + 
	 		"	 		set @CodeSite=:codesite \r\n" + 
	 		"			set @idclient = :idclient\r\n" + 
	 		"	 		set @TypeFacture = 'D' \r\n" + 
	 		"	 		set @CptFac=isnull((select max(right(NumFacture,5)) from Matu_otoFactureMono \r\n" + 
	 		"			where SUBSTRING(NumFacture,5,3)=@CodeSite and left(NumFacture,4)=@Ann and TypeFacture=@TypeFacture\r\n" + 
	 		"			and idclient=@idclient),0) \r\n" + 
	 		"	 		set @CptFac=@CptFac \r\n" + 
	 		"	 		select NumFacture =  @Ann+@CodeSite+@TypeFacture+replicate('0',5-len(@CptFac))+cast(@CptFac as varchar(5))")
		String get_current_facture(@Param ("codesite") String  codesite,@Param ("exercice") String  exercice,@Param ("idclient") Integer  idclient);

	 @Query(nativeQuery = true, value = " declare @NumFacture varchar(15),@CodeSite varchar(3),@Ann varchar(4),@TypeFacture varchar(4) \r\n" + 
		 		"	 		declare @CptFac int,@idclient int \r\n" + 
		 		"	 		set @Ann=:exercice \r\n" + 
		 		"	 		set @CodeSite=:codesite \r\n" + 
		 		"			set @idclient = :idclient\r\n" + 
		 		"	 		set @TypeFacture = 'G' \r\n" + 
		 		"	 		set @CptFac=isnull((select max(right(NumFacture,5)) from Matu_otoFactureMono \r\n" + 
		 		"			where SUBSTRING(NumFacture,5,3)=@CodeSite and left(NumFacture,4)=@Ann and TypeFacture=@TypeFacture\r\n" + 
		 		"			and idclient=@idclient),0) \r\n" + 
		 		"	 		set @CptFac=@CptFac \r\n" + 
		 		"	 		select NumFacture =  @Ann+@CodeSite+@TypeFacture+replicate('0',5-len(@CptFac))+cast(@CptFac as varchar(5))")
			String get_current_facture_globale(@Param ("codesite") String  codesite,@Param ("exercice") String  exercice,@Param ("idclient") Integer  idclient);
	 
	 
	 @Modifying
	 @Query(value = "declare @Msg_ varchar(500) ,@NomFac varchar(15)\r\n" + 
	 		"exec [dbo].[ps_MatuFac_InsererFactureMono] :idsite, :idclient,:exercice,'G',:quittances,:idoperateur,@NomFac  output,  @Msg_  output \r\n" + 
	 		"print @NomFac", nativeQuery = true)
	    @Transactional
	    void insererfactureglobale(@Param ("idsite") Integer  idsite,@Param ("idclient") Integer  idclient,@Param ("exercice") String  exercice,@Param ("quittances") String  quittances,@Param ("idoperateur") Long  idoperateur);
	 
}
