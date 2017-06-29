package br.com.luismatos.controller;


import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.luismatos.dao.TarefaDAO;
import br.com.luismatos.model.Tarefa;

@Controller
public class TarefasController {

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
		TarefaDAO dao = new TarefaDAO();
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/finalizada";
	}

	/*
	 * @RequestMapping("listaTarefas") public ModelAndView lista() { TarefaDAO
	 * dao = new TarefaDAO(); List<Tarefa> tarefas = dao.lista(); ModelAndView
	 * mv = new ModelAndView("tarefa/lista"); mv.addObject("tarefas", tarefas);
	 * return mv; }
	 */
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		TarefaDAO dao = new TarefaDAO();
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
}