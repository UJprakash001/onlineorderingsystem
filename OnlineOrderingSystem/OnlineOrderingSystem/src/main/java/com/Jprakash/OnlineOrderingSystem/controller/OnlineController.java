package com.Jprakash.OnlineOrderingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Jprakash.OnlineOrderingSystem.service.TicketTrackerService;
import com.Jprakash.OnlineOrderingSystem.entity.*;

@Controller
@RequestMapping("/")
public class OnlineController {
	// injecting ticket serviceimpl
	@Autowired
	private TicketTrackerService ticketServiceImpl;

	// index page
	@GetMapping("/tickets")
	public String mainPage(Model theModel) {

		List<Order> ticket = ticketServiceImpl.getAllTickets();
		theModel.addAttribute("ticket", ticket);
		return "ticket";
	}

	// new ticket page
	@GetMapping("/newPage")
	public String newTicketPage() {
		return "create_ticket";
	}

	// adding new data
	@PostMapping("/create")
	public String createTicket(@ModelAttribute Order t) {
		System.out.println(t);

		ticketServiceImpl.addTicket(t);
		return "redirect:/tickets";

	}

	// viewing the edit page
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable int id, Model themodel) {

		Order t = ticketServiceImpl.getTicketById(id);
		themodel.addAttribute("ticket", t);
		return "edit_ticket";

	}

	// updating the changes
	@PostMapping("/update")
	public String updateTicket(@ModelAttribute Order t) {

		ticketServiceImpl.addTicket(t);
		return "redirect:/tickets";

	}

	// delete ticket
	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id, Model themodel) {
		ticketServiceImpl.delTicket(id);
		return "redirect:/tickets";
	}

	// viewing ticket
	@GetMapping("/view/{id}")
	public String viewPage(@PathVariable int id, Model theModel) {

		Order ticket = ticketServiceImpl.getTicketById(id);
		theModel.addAttribute("ticket", ticket);
		return "view_ticket";

	}

	// going back to index page
	@PostMapping("/submit")
	public String submitTicket(@ModelAttribute Order t) {

		return "redirect:/tickets";

	}

	// search functionality
	@GetMapping(path = { "/search" })
	public String home(Order ticket, Model model, String keyword) {
		if (keyword != null) {
			List<Order> ticket1 = ticketServiceImpl.getByKeyword(keyword);
			model.addAttribute("ticket", ticket1);
		} else {
			List<Order> list = ticketServiceImpl.getAllTickets();
			model.addAttribute("list", list);
		}
		return "ticket";
	}
}
