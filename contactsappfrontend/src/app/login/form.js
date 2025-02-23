"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function LoginForm() {
  const router = useRouter();
  const [email, setEmail] = useState("");
 
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (event) => {
    event.preventDefault();
    setError("");
    setLoading(true);

    try {
      // Call your backend API URL here:
      const response = await fetch(`http://localhost:8080/checkUser?username=${email}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
        
      });

      const data = await response.json();
      console.log(data)
      if (data.exists) {
        // If user exists, navigate to the contacts page
        router.push(`/contacts?username=${encodeURIComponent(email)}`);
      } else {
        setError("User does not exist. Please try again.");
      }
     
    } catch (err) {
      setError("Something went wrong. Please try again later.");
      console.log(err)
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleLogin} className="space-y-4">
      <div>
        <label className="block text-sm font-medium mb-1">Email</label>
        <input
          type="text"
          className="w-full p-2 border border-gray-300 rounded"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>

     

      {error && <p className="text-red-500 text-sm">{error}</p>}

      <button
        type="submit"
        className="w-full p-2 bg-blue-600 text-white rounded hover:bg-blue-700"
        disabled={loading}
      >
        {loading ? "Logging in..." : "Login"}
      </button>
    </form>
  );
}
