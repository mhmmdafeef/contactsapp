"use client";

import React, { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";

export default function ContactsPage() {
  const searchParams = useSearchParams();
  const username = searchParams.get("username");
  const [contacts, setContacts] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // Track which contact is being edited
  const [editingId, setEditingId] = useState(null);
  const [editName, setEditName] = useState("");
  const [editContactNumber, setEditContactNumber] = useState("");

  // For adding a new contact
  const [isAdding, setIsAdding] = useState(false);
  const [newName, setNewName] = useState("");
  const [newContactNumber, setNewContactNumber] = useState("");

  useEffect(() => {
    if (!username) {
      setError("No username provided.");
      return;
    }

    const fetchContacts = async () => {
      setLoading(true);
      try {
        const response = await fetch(
          `http://localhost:8080/getContacts?username=${encodeURIComponent(username)}`,
          {
            method: "GET",
            headers: { "Content-Type": "application/json" },
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch contacts.");
        }
        const data = await response.json();
        setContacts(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchContacts();
  }, [username]);

  const handleEditClick = (id, name, contactNumber) => {
    setEditingId(id);
    setEditName(name);
    setEditContactNumber(contactNumber);
  };

  const handleSave = async (contact) => {
    try {
      const updatedContact = {
        ...contact, // Keep all existing properties
        name: editName, // Update only the name
        contactNumber: editContactNumber,
      };
      console.log(JSON.stringify(updatedContact));
      const response = await fetch("http://localhost:8080/editContact", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedContact),
      });
      console.log(response);

      if (!response.ok) throw new Error("Failed to update contact.");

      // Update UI instantly (Optimistic Update)
      setContacts((prevContacts) =>
        prevContacts.map((c) => (c.id === contact.id ? updatedContact : c))
      );

      setEditingId(null); // Exit edit mode
    } catch (err) {
      console.error("Error updating contact:", err);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/deletecontact?id=${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      });

      if (!response.ok) {
        throw new Error(`Error: ${response.status} ${response.statusText}`);
      }

      console.log(`Contact with ID ${id} deleted successfully`);

      // Update the state to remove the deleted contact
      setContacts((prevContacts) => prevContacts.filter((contact) => contact.id !== id));
    } catch (err) {
      console.error("Failed to delete contact:", err.message);
      setError(err.message);
    }
  };

  // Handlers for adding a new contact
  const handleAddClick = () => {
    setIsAdding(true);
  };

  const handleAddSave = async () => {
    if (!newName.trim() || !newContactNumber.trim()) {
      alert("Please enter both name and contact number.");
      return;
    }
    try {
      const newContact = {
        user: username,// if needed by the backend
        name: newName,
        contactNumber: newContactNumber,
      };

      console.log(newContact)

      const response = await fetch("http://localhost:8080/addContact", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newContact),
      });

      if (!response.ok) {
        setIsAdding(false);
        throw new Error("Failed to add contact.");
      }

      const addedContact = await response.json();
      setContacts((prevContacts) => [...prevContacts, addedContact]);
      setIsAdding(false);
      setNewName("");
      setNewContactNumber("");
    } catch (err) {
      console.error("Error adding contact:", err);
      setError(err.message);
    }
  };

  const handleAddCancel = () => {
    setIsAdding(false);
    setNewName("");
    setNewContactNumber("");
  };

  if (loading) return <p>Loading contacts...</p>;
  if (error) return <p className="text-red-600">{error}</p>;

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Contacts for {username}</h1>
      {contacts.length === 0 && !isAdding ? (
        <p>No contacts found.</p>
      ) : (
        <table className="min-w-full bg-white shadow-md rounded">
          <thead className="bg-gray-200">
            <tr>
              <th className="p-2 border">Name</th>
              <th className="p-2 border">Contact Number</th>
              <th className="p-2 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            {contacts.map((contact) => (
              <tr key={contact.id}>
                <td className="p-2 border">
                  {editingId === contact.id ? (
                    <input
                      type="text"
                      value={editName}
                      onChange={(e) => setEditName(e.target.value)}
                      className="p-1 border border-gray-300 rounded"
                    />
                  ) : (
                    contact.name
                  )}
                </td>
                <td className="p-2 border">
                  {editingId === contact.id ? (
                    <input
                      type="text"
                      value={editContactNumber}
                      onChange={(e) => setEditContactNumber(e.target.value)}
                      className="p-1 border border-gray-300 rounded"
                    />
                  ) : (
                    contact.contactNumber
                  )}
                </td>
                <td className="p-2 border">
                  {editingId === contact.id ? (
                    <button
                      onClick={() => handleSave(contact)}
                      className="mr-2 px-3 py-1 bg-green-600 text-white rounded hover:bg-green-700"
                    >
                      Save
                    </button>
                  ) : (
                    <button
                      onClick={() =>
                        handleEditClick(contact.id, contact.name, contact.contactNumber)
                      }
                      className="mr-2 px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700"
                    >
                      Edit
                    </button>
                  )}
                  <button
                    onClick={() => handleDelete(contact.id)}
                    className="px-3 py-1 bg-red-600 text-white rounded hover:bg-red-700"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
            {isAdding && (
              <tr>
                <td className="p-2 border">
                  <input
                    type="text"
                    value={newName}
                    onChange={(e) => setNewName(e.target.value)}
                    placeholder="Enter name"
                    className="p-1 border border-gray-300 rounded"
                  />
                </td>
                <td className="p-2 border">
                  <input
                    type="text"
                    value={newContactNumber}
                    onChange={(e) => setNewContactNumber(e.target.value)}
                    placeholder="Enter contact number"
                    className="p-1 border border-gray-300 rounded"
                  />
                </td>
                <td className="p-2 border">
                  <button
                    onClick={handleAddSave}
                    className="mr-2 px-3 py-1 bg-green-600 text-white rounded hover:bg-green-700"
                  >
                    Save
                  </button>
                  <button
                    onClick={handleAddCancel}
                    className="px-3 py-1 bg-gray-600 text-white rounded hover:bg-gray-700"
                  >
                    Cancel
                  </button>
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}
      {!isAdding && (
        <button
          onClick={handleAddClick}
          disabled={isAdding}
          className="mt-4 px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700"
        >
          Add Contact
        </button>
      )}
    </div>
  );
}
