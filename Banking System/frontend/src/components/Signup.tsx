// Signup component
// Task checklist:
// - Build a form with inputs: email, password
// - Validate: email format, password >= 6 (optionally add complexity)
// - On submit: POST /api/user/signup with { email, plainPassword }
// - On success: Option A: automatically invoke login; Option B: switch to Login view
// - Show loading and errors (use res.text() for server error messages)
//
// Example (fetch usage):
// async function handleSignup(e) {
//   e.preventDefault();
//   const res = await fetch('/api/user/signup', {
//     method: 'POST', headers: { 'Content-Type': 'application/json' },
//     body: JSON.stringify({ email, plainPassword: password })
//   });
//   if (!res.ok) throw new Error(await res.text());
// }

export default function Signup() {
  return (
    <div>
      {/* TODO: Implement Signup form (email, password); call /api/user/signup; handle success & errors */}
    </div>
  );
} 