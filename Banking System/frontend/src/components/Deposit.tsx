// Deposit component
// Task checklist:
// - Build a form with inputs: accountNumber, amount
// - Validate: amount > 0
// - Read token: const token = localStorage.getItem('jwtToken')
// - On submit: POST /api/accounts/:accountNumber/deposit with body { amount }
// - Headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` }
// - On success: show confirmation and possibly updated balance from response
// - Handle loading and errors
//
// Example (fetch usage):
// async function handleDeposit(e) {
//   e.preventDefault();
//   const token = localStorage.getItem('jwtToken');
//   const res = await fetch(`/api/accounts/${accountNumber}/deposit`, {
//     method: 'POST',
//     headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
//     body: JSON.stringify({ amount })
//   });
//   if (!res.ok) throw new Error(await res.text());
//   const data = await res.json(); // may contain updated account
// }

export default function Deposit() {
  return (
    <div>
      {/* TODO: Implement Deposit form (accountNumber, amount); call backend; handle success & errors */}
    </div>
  );
} 