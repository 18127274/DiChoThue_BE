using API_PTUD_R5.Model;
using API_PTUD_R5.Service;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace API_PTUD_R5.Controller
{
    [Route("api/sanpham")]
    [ApiController]
    public class SANPHAMController : ControllerBase
    {
        private readonly SANPHAMService _bookService;

        public SANPHAMController(SANPHAMService bookService)
        {
            _bookService = bookService;
        }

        [HttpGet]
        public ActionResult<List<SANPHAM>> Get() =>
            _bookService.Get();

        [HttpGet]
        public ActionResult<SANPHAM> Get(string id)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            return book;
        }

        [HttpPost]
        public ActionResult<SANPHAM> Create(SANPHAM book)
        {
            if (book.Min > 0 && book.Max <= book.SL)
            {
                _bookService.Create(book);
                return CreatedAtRoute("GetBook", new { id = book.Id.ToString() }, book);
            }
            return BadRequest();
        }

        [HttpPut("{id}")]
        public IActionResult Update(string id, SANPHAM bookIn)
        {
            var book = _bookService.Get(id);

            if (book == null)
            {
                return NotFound();
            }

            _bookService.Update(id, bookIn);

            return NoContent();
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

