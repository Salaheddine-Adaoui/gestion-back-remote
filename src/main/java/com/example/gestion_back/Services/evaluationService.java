package com.example.gestion_back.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.evaluationDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Evaluation;
import com.example.gestion_back.Repository.elementRepo;
import com.example.gestion_back.Repository.evaluationRepo;

@Service
public class evaluationService {
	
	
	@Autowired
	evaluationRepo evalrepo;
	
	@Autowired
	elementRepo elemrepo;
	
	public String addEvaluation(evaluationDto evalDto) {
		
		Optional<Element> el=elemrepo.findById(evalDto.getElid());
		if(el.isPresent()) {
			Element e=el.get();
			if(evalDto.isCc()) {
				Evaluation eval=new Evaluation();
				eval.setType("CC");
				eval.setCoiff(evalDto.getCccoif());
				eval.setElement(e);
				evalrepo.save(eval);
			}
			if(evalDto.isTp()) {
				Evaluation eval=new Evaluation();
				eval.setType("TP");
				eval.setCoiff(evalDto.getTpcoif());
				eval.setElement(e);
				evalrepo.save(eval);
			}
			if(evalDto.isPres()) {
				Evaluation eval=new Evaluation();
				eval.setType("Presentation");
				eval.setCoiff(evalDto.getPrescoif());
				eval.setElement(e);
				evalrepo.save(eval);
			}
			if(evalDto.isProj()) {
				Evaluation eval=new Evaluation();
				eval.setType("Projet");
				eval.setCoiff(evalDto.getProjcoif());
				eval.setElement(e);
				evalrepo.save(eval);
			}
			return "succes";
		}
		return "failed";
	}
	
	public List<Map<String,Object>> elemnthasNoteEval(){
		List<Evaluation> evals = evalrepo.findAll();
		List<Element> elms = elemrepo.findAll();

		
		Set<Long> evaluatedElementIds = evals.stream()
		    .map(e->e.getElement().getId()) 
		    .collect(Collectors.toSet());

		List<Element> elemnothasnoteval = elms.stream()
		    .filter(e -> !evaluatedElementIds.contains(e.getId()))
		    .collect(Collectors.toList());
		List<Map<String,Object>> result=new ArrayList<>();
		for(Element i:elemnothasnoteval) {
			Map<String,Object> map=new HashMap<>();
			map.put("id", i.getId());
			map.put("nom", i.getNom());
			result.add(map);
		}

		return result;
	}
	
	public List<Map<String,Object>> allEvaluations() {
		List<Map<String,Object>> result=new ArrayList<>();
		
		List<Evaluation> evals = evalrepo.findAll();
		for(Evaluation i:evals) {
			Map<String,Object> map=new HashMap<>();
			map.put("id", i.getId());
			map.put("nomel", i.getElement().getNom());
			map.put("nommod", i.getElement().getModule().getNom());
			map.put("nomfil",i.getElement().getModule().getFilier()!=null? i.getElement().getModule().getFilier().getNom():"");
			map.put("nivfil",i.getElement().getModule().getFilier()!=null? i.getElement().getModule().getFilier().getNiveau():"");
			map.put("nomeval", i.getType());
			map.put("coideval", i.getCoiff());
			result.add(map);
		}
		return result;
	}
	
	public String deleteEvaluation(Long id) {
		Optional<Evaluation> ev=evalrepo.findById(id);
		if(ev.isPresent()){
			Evaluation e=ev.get();
			evalrepo.delete(e);
			return "succes";
		}
		return "failed";
	}
	
	public List<Map<String,Object>> evaluationDEelement(Long id){
		List<Evaluation> evaluations=evalrepo.findByElementId(id);
		List<Map<String,Object>> list=new ArrayList<>();
		
		for(Evaluation ev:evaluations) {
			Map<String,Object> map=new HashMap<>();
			map.put("idev", ev.getId());
			map.put("type", ev.getType());
			
			list.add(map);
		}
		return list;
	}
	
	
	
	

}
