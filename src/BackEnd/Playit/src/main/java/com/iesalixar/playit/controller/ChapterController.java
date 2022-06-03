package com.iesalixar.playit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesalixar.playit.dto.ChapterDTO;
import com.iesalixar.playit.model.Chapter;
import com.iesalixar.playit.model.Serie;
import com.iesalixar.playit.service.ChapterServiceImpl;
import com.iesalixar.playit.service.SerieServiceImpl;

@Controller
public class ChapterController {
	@Autowired
	ChapterServiceImpl chapterService;

	@Autowired
	SerieServiceImpl serieService;

	Chapter chapterAux;

	@GetMapping("/chapter")
	public String chapterGet(@RequestParam(required = false, name = "chapterDeletedId") String chapterDeletedId,
			@RequestParam(required = false, name = "chapterEdited") String chapterEdited,
			@RequestParam(required = false, name = "chapterAdded") String chapterAdded, Model model) {
		List<Chapter> chapters = chapterService.getAllChapters();

		model.addAttribute("chapters", chapters);
		model.addAttribute("chapterDeletedId", chapterDeletedId);
		model.addAttribute("chapterEdited", chapterEdited);
		model.addAttribute("chapterAdded", chapterAdded);
		return "/admin/chapter";
	}

	@GetMapping("/chapter/add")
	public String addChapterGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "chapterName") String nombre, Model model) {

		ChapterDTO chapter = new ChapterDTO();
		List<Serie> series = serieService.getAllSeries();

		model.addAttribute("series", series);
		model.addAttribute("chapter", chapter);
		model.addAttribute("error", error);
		model.addAttribute("chapterName", nombre);

		return "admin/addChapter";
	}

	@PostMapping("/chapter/add")
	public String addChapterPost(@ModelAttribute ChapterDTO chapter, Model model) {

		Chapter chapterDB = new Chapter();
		chapterDB.setName(chapter.getName());
		chapterDB.setNumber(chapter.getNumber());
		chapterDB.setSeason(chapter.getSeason());
		chapterDB.setSerie(serieService.getSerieByID(chapter.getSerieId()));

		if (chapterService.addChapter(chapterDB) == null) {
			System.out.println(chapter.getName());
			return "redirect:/chapter/add?error=Exist&chapterName=" + chapter.getName();
		}

		return "redirect:/chapter?chapterAdded=ok";
	}

	@GetMapping("/chapter/delete")
	public String deleteChapterGet(@RequestParam(required = false, name = "chapterId") String id) {
		Chapter chapter = new Chapter();
		chapter = chapterService.deleteChapter(Long.parseLong(id));
		Long idChapter = chapter.getChapterId();
		return "redirect:/chapter?chapterDeletedId=" + idChapter;
	}

	@GetMapping("/chapter/edit")
	public String editChapterGet(@RequestParam(required = true, name = "chapterId") String id,
			@RequestParam(required = false, name = "error") String error, Model model) {
		chapterAux = new Chapter();

		List<Serie> series = serieService.getAllSeries();

		Chapter chapter = chapterService.getChapterByID(Long.parseLong(id));
		chapterAux.setChapterId(chapter.getChapterId());

		ChapterDTO chapterDTO = new ChapterDTO();
		chapterDTO.setName(chapter.getName());
		chapterDTO.setNumber(chapter.getNumber());
		chapterDTO.setSeason(chapter.getSeason());
		chapterDTO.setSerieId(chapter.getSerie().getContentId());

		model.addAttribute("chapterDTO", chapterDTO);
		model.addAttribute("error", error);
		model.addAttribute("series", series);

		return "admin/editChapter";
	}

	@PostMapping("/chapter/edit")
	public String editChapterPost(@ModelAttribute ChapterDTO chapter, Model model) {
		Chapter chapterDB = new Chapter();

		chapterDB.setChapterId(chapterAux.getChapterId());
		chapterDB.setName(chapter.getName());
		chapterDB.setNumber(chapter.getNumber());
		chapterDB.setSeason(chapter.getSeason());
		chapterDB.setSerie(serieService.getSerieByID(chapter.getSerieId()));

		if (chapterService.editChapter(chapterDB) == null) {
			System.out.println(chapterDB.getChapterId());
			return "redirect:/chapter/edit?error=Exist&chapterId=" + chapterDB.getChapterId();
		}

		return "redirect:/chapter?chapterEdited=ok";
	}
}
