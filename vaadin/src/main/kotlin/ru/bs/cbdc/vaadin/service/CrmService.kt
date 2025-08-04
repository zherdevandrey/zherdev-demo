package ru.bs.cbdc.vaadin.service


import org.springframework.stereotype.Service
import ru.bs.cbdc.vaadin.entity.Company
import ru.bs.cbdc.vaadin.entity.Contact
import ru.bs.cbdc.vaadin.entity.Status
import ru.bs.cbdc.vaadin.repository.CompanyRepository
import ru.bs.cbdc.vaadin.repository.ContactRepository
import ru.bs.cbdc.vaadin.repository.StatusRepository

@Service
class CrmService(
    private val contactRepository: ContactRepository,
    private val companyRepository: CompanyRepository,
    private val statusRepository: StatusRepository
) {

    fun findAllContacts(stringFilter: String?): List<Contact?>? {
        return if (stringFilter == null || stringFilter.isEmpty()) {
            contactRepository.findAll()
        } else {
            contactRepository.search(stringFilter)
        }
    }

    fun countContacts(): Long {
        return contactRepository.count()
    }

    fun deleteContact(contact: Contact?) {
        contact?.let {
            contactRepository.delete(it)
        }
    }

    fun saveContact(contact: Contact?) {
        if (contact == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?")
            return
        }
        contactRepository.save(contact)
    }

    fun findAllCompanies(): List<Company> {
        return companyRepository.findAll()
    }

    fun findAllStatuses(): List<Status> {
        return statusRepository.findAll()
    }
}