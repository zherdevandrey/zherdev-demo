package ru.bs.cbdc.vaadin.view

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.ComponentEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.shared.Registration
import org.springframework.context.support.beans
import ru.bs.cbdc.vaadin.entity.Company
import ru.bs.cbdc.vaadin.entity.Contact
import ru.bs.cbdc.vaadin.entity.Status

class ContactForm(companies: List<Company>, statuses: List<Status>) : FormLayout() {

    val firstName = TextField("First name")
    val lastName = TextField("Last name")
    val email = TextField("Email")

    val status = ComboBox<Status>("Status")
    val company = ComboBox<Company>("Company")

    var save = Button("Save")


    var delete = Button("Delete")
    var close = Button("Cancel")

    val binder = BeanValidationBinder(Contact::class.java)

    init {
        val buttonsLayout = createButtonsLayout()

        binder.bindInstanceFields(this)
        status.setItems(statuses)
        status.setItemLabelGenerator { it.name }

        company.setItems(companies)
        company.setItemLabelGenerator { it.name }

        add(
            firstName,
            lastName,
            email,
            status,
            company,
            buttonsLayout
        )
    }

    private fun createButtonsLayout(): Component {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR)
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY)

        save.addClickListener { validateAndSave() }
        delete.addClickListener { deleteContact() }

        return HorizontalLayout(save, delete, close)
    }

    private fun deleteContact() {
        fireEvent(DeleteEvent(this, binder.bean ))
    }

    fun setContact(contact: Contact?) {
        binder.bean = contact
    }

    private fun validateAndSave() {
        if (binder.isValid) {
            fireEvent(SaveEvent(this, binder.bean))
        }
    }


    open class ContactFormEvent(source: ContactForm?, val contact: Contact?) : ComponentEvent<ContactForm?>(source, false)

    class SaveEvent internal constructor(source: ContactForm?, contact: Contact?) :
        ContactFormEvent(source, contact!!)

    class DeleteEvent internal constructor(source: ContactForm?, contact: Contact?) :
        ContactFormEvent(source, contact!!)

    class CloseEvent internal constructor(source: ContactForm?) : ContactFormEvent(source, null)

    fun addDeleteListener(listener: ComponentEventListener<DeleteEvent?>?): Registration {
        return addListener(DeleteEvent::class.java, listener)
    }

    fun addSaveListener(listener: ComponentEventListener<SaveEvent?>?): Registration {
        return addListener(SaveEvent::class.java, listener)
    }

    fun addCloseListener(listener: ComponentEventListener<CloseEvent?>?): Registration {
        return addListener(CloseEvent::class.java, listener)
    }
}