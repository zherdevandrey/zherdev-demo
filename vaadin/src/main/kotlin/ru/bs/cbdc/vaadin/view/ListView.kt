package ru.bs.cbdc.vaadin.view

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import ru.bs.cbdc.vaadin.entity.Contact
import ru.bs.cbdc.vaadin.service.CrmService

@PageTitle("Contacts | Vaadin CRM")
@Route("")
class ListView(val service: CrmService) : VerticalLayout() {

    private val grid = Grid(Contact::class.java)
    private val filterText = TextField()
    private val form = ContactForm(service.findAllCompanies(), service.findAllStatuses())


    init {
        configureGrid()
        configureForm()
        val toolBar = getToolBar()

        add(toolBar, getContent())
        updateList()

    }

    private fun configureGrid() {
        grid.addClassName("contact-grid")
        grid.setSizeFull()
        grid.setHeight("80em")
        grid.setColumns("firstName", "lastName", "email")

        grid.addColumn{ it.status!!.name }.setHeader("Status")
        grid.addColumn{ it.company!!.name }.setHeader("Company")

        grid.columns.forEach{ it.setAutoWidth(true) }

        grid.asSingleSelect().addValueChangeListener { editContact(it.value) }

    }

    private fun editContact(contact: Contact?) {
        if (contact == null) {
            closeEdit()
        }else {
            form.setContact(contact)
            form.isVisible = true
        }
    }

    private fun closeEdit() {
        form.setContact(null)
        form.isVisible = false
    }

    private fun configureForm() {
        form.setWidth("25em");
        form.addSaveListener{event -> saveContact(event)}
        form.addDeleteListener{event -> deleteContract(event)}
        closeEditor()
    }

    private fun deleteContract(event: ContactForm.DeleteEvent?) {
        event?.let { service.deleteContact(it.contact) }
        updateList()
        closeEdit()
    }

    private fun saveContact(event:ContactForm.SaveEvent?) {
        event?.let { service.saveContact(it.contact) }
        updateList()
        closeEdit()
    }


    private fun getContent():HorizontalLayout {
        val horizontalLayout = HorizontalLayout(grid, form)
        horizontalLayout.setFlexGrow(2.0, grid)
        horizontalLayout.setFlexGrow(1.0, form)
        horizontalLayout.setSizeFull()
        horizontalLayout.setHeightFull()
        return horizontalLayout
    }

    private fun getToolBar(): Component {
        filterText.placeholder =  "Filter by name"
        filterText.isClearButtonVisible = true
        filterText.valueChangeMode = ValueChangeMode.EAGER
        filterText.addValueChangeListener { updateList() }


        val addContactButton = Button("Add Contact")
        val toolbar  = HorizontalLayout(filterText, addContactButton)
        return toolbar
    }

    private fun updateList() {
        grid.setItems(service.findAllContacts(filterText.value));
    }

    private fun closeEditor() {
        form.setContact(null)
        form.isVisible = false
    }
}