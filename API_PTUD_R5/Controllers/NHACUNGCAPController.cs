using API_PTUD_R5.Model;
using API_PTUD_R5.Service;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace API_PTUD_R5.Controller
{
    [Route("api/nhacungcap")]
    [ApiController]
    public class NHACUNGCAPController : ControllerBase
    {
        private readonly NHACUNGCAPService _bookService;

        public NHACUNGCAPController(NHACUNGCAPService bookService)
        {
            _bookService = bookService;
        }

        [HttpGet]
        public ActionResult<List<NHACUNGCAP>> Get()
        {
            System.Diagnostics.Debug.WriteLine("tring");
           return _bookService.Get();

        }
            

        [HttpGet("{id}")]
        public ActionResult<NHACUNGCAP> Get(string id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            return book;
        }

        [HttpPost]
        public ActionResult<NHACUNGCAP> Create(NHACUNGCAP book)
        {
            NHACUNGCAP temp = _bookService.Create(book);
            if (temp!=null)
                return Ok(book);
            return BadRequest();
        }

        [HttpPut("{id}")]
        public IActionResult Update(string id, NHACUNGCAP bookIn)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Update(id, bookIn);

            return Ok(bookIn);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Remove(book.Id);

            return NoContent();
        }
    }
}
